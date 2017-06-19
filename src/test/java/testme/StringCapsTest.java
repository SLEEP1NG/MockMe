package testme;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.*;
import static matcher.threecap.ThreeCap.hasThreeCaps;
import static org.hamcrest.CoreMatchers.not;

public class StringCapsTest {
  @Test
  public void threeCapsWorks() throws Throwable {
    String target = "Hello THere";
    assertThat(target, hasThreeCaps());
  }

  @Test
  public void twoCapsFails() throws Throwable {
    String target = "Hello There";
    assertThat(target, not(hasThreeCaps()));
  }

  @Test
  public void fourCapsFails() throws Throwable {
    String target = "HellO TherE";
    assertThat(target, not(hasThreeCaps()));
  }
}
