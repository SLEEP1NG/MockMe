package matcher.threecap;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class ThreeCap extends TypeSafeMatcher<String> {
  private ThreeCap() {}
  
  public static TypeSafeMatcher<String> hasThreeCaps() {
    return new ThreeCap();
  }

  
  @Override
  protected boolean matchesSafely(String t) {
    char [] chars = t.toCharArray();
    int capCount = 0;
    for (char c : chars) {
      if (Character.isUpperCase(c)) capCount++;
    }
    return capCount == 3;
  }

  @Override
  public void describeTo(Description d) {
    d.appendText("<has three upper case characters>");
  }
}
