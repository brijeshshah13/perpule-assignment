package perpule.brijesh.perpuleassignment.ui.welcome;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import perpule.brijesh.perpuleassignment.R;

import static junit.framework.Assert.assertNotNull;

public class WelcomeActivityTest {

    @Rule
    public ActivityTestRule<WelcomeActivity> mActivityTestRule = new ActivityTestRule<>(WelcomeActivity.class);

    private WelcomeActivity mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch() {
        View mStartBtn = mActivity.findViewById(R.id.btn_start);
        View mLogoIV = mActivity.findViewById(R.id.iv_logo);
        assertNotNull(mStartBtn);
        assertNotNull(mLogoIV);
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}