package com.antonkuzmn1.xqwkeburenochekshrooms.client.itemstates;

import com.antonkuzmn1.xqwkeburenochekshrooms.client.itemstates.common.ItemTransform;

public abstract class ItemState {
    public static ItemTransform base = new ItemTransform();
    public static ItemTransform gui = new ItemTransform();
    public static ItemTransform thirdPerson = new ItemTransform();
    public static ItemTransform firstPerson = new ItemTransform();
}
