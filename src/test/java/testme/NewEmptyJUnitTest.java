package testme;

import com.dancingcloudservices.mockresultset.MockResultSet;
import com.mycompany.mockme.DoDbStuff;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.hamcrest.MatcherAssert;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NewEmptyJUnitTest {

//  @Mock
//  DataSource ds;
  @Mock
  Driver driver;
  @Mock
  Connection conn;
  @Mock
  Statement stat;
  @Mock
  ResultSet rs;

  @InjectMocks
  DoDbStuff underTest;

//  @Before
//  public void setupMocks() {
//    MockitoAnnotations.initMocks(this);
//  }
  
  @Test
  public void lookupDataFindsSingleItem() throws Throwable {
//    Mockito.when(ds.getConnection()).thenReturn(conn);
    Mockito.when(driver.connect(Mockito.any(), Mockito.any())).thenReturn(conn);
    Mockito.when(conn.createStatement()).thenReturn(stat);
    Mockito.when(stat.executeQuery("SELECT * FROM SIMON.STUFF"))
        .thenReturn(rs);
    Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
    Mockito.when(rs.getString(1))
        .thenReturn("Valid")
        .thenThrow(SQLException.class);
    Mockito.when(rs.getString(2))
        .thenReturn("Albert")
        .thenThrow(SQLException.class);

    MatcherAssert.assertThat(
        underTest.lookupData(),
        CoreMatchers.equalTo("results: Albert, ")
    );
  }

//  @Ignore
  @Test
  public void lookupDataFindsMoreItems() throws Throwable {
//    Mockito.when(ds.getConnection()).thenReturn(conn);
    Mockito.when(driver.connect(Mockito.any(), Mockito.any())).thenReturn(conn);
    Mockito.when(conn.createStatement()).thenReturn(stat);
    Mockito.when(stat.executeQuery(Mockito.any()))
        .thenReturn(
            new MockResultSet(new String[][]{
              {"Valid", "Albert"},
              {"Valid", "Jimmy"}
            })
        );

    MatcherAssert.assertThat(
        underTest.lookupData(),
        CoreMatchers.equalTo("results: Albert, Jimmy, ")
    );
  }
}
