package com.antonkuzmn1.xqwkeburenochekshrooms.client.renderers;

import com.antonkuzmn1.xqwkeburenochekshrooms.client.itemstates.ToadstoolItemState;
import com.antonkuzmn1.xqwkeburenochekshrooms.client.models.ToadstoolItemModel;
import com.antonkuzmn1.xqwkeburenochekshrooms.items.ToadstoolItem;

public class ToadstoolItemRenderer extends ItemRenderer<ToadstoolItem, ToadstoolItemModel> {
    public ToadstoolItemRenderer() {
        super(
                new ToadstoolItemModel(),
                ToadstoolItemState.base,
                ToadstoolItemState.gui,
                ToadstoolItemState.thirdPerson,
                ToadstoolItemState.firstPerson
        );
    }
}
