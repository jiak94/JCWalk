package com.erganellc.www.jcmemorialwalkapp;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by Jiakuan on 12/25/2015.
 */
public class BlockActivityTest extends ActivityInstrumentationTestCase2<BlockActivity>{
    private BlockActivity mBlockActivity;
    private ImageButton start, wwi, wwii, korea, viet, gulf, iraq, afghan, women, purple, pow;

    public BlockActivityTest() {
        super(BlockActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mBlockActivity = getActivity();

        start = (ImageButton) mBlockActivity.findViewById(R.id.intro);
        wwi = (ImageButton)mBlockActivity.findViewById(R.id.wwi);
        wwii = (ImageButton)mBlockActivity.findViewById(R.id.wwii);
        korea = (ImageButton)mBlockActivity.findViewById(R.id.korea);
        viet = (ImageButton)mBlockActivity.findViewById(R.id.vietnam);
        gulf = (ImageButton)mBlockActivity.findViewById(R.id.gulf);
        iraq = (ImageButton)mBlockActivity.findViewById(R.id.iraq);
        afghan = (ImageButton)mBlockActivity.findViewById(R.id.afghan);
        women = (ImageButton)mBlockActivity.findViewById(R.id.women);
        purple = (ImageButton)mBlockActivity.findViewById(R.id.purple);
        pow = (ImageButton)mBlockActivity.findViewById(R.id.pow);
    }

    public void testPredictions() {
        assertNotNull("mBlockActivity is null", mBlockActivity);
        assertNotNull("start is null", start);
        assertNotNull("wwii is null", wwii);
        assertNotNull("wwi is null", wwi);
        assertNotNull("korea is null", korea);
        assertNotNull("viet is null", viet);
        assertNotNull("gulf is null", gulf);
        assertNotNull("iraq is null", iraq);
        assertNotNull("afghan is null", afghan);
        assertNotNull("women is null", women);
        assertNotNull("purple is null", purple);
        assertNotNull("pow is null", pow);
    }
}
