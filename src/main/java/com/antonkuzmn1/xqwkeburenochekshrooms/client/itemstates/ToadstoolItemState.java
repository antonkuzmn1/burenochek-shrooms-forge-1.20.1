package com.antonkuzmn1.xqwkeburenochekshrooms.client.itemstates;

import com.antonkuzmn1.xqwkeburenochekshrooms.client.itemstates.common.ItemTransform;

public final class ToadstoolItemState extends ItemState {
    public static ItemTransform base = new ItemTransform(
            1.0f,
            0.0f, 0.0f, 0.0f
    );

    public static ItemTransform gui = new ItemTransform(
            0.8f,
            -0.05f, -0.2f, 0.0f,
            30f, 45f, 0f
    );

    public static ItemTransform thirdPerson = new ItemTransform(
            0.5f,
            0.3f, 0.8f, 0.2f,
            60f, 45f, 0f
    );

    public static ItemTransform firstPerson = new ItemTransform(
            0.5f,
            0.3f, 0.5f, 1.2f,
            0f, 45f, 0f
    );
}
