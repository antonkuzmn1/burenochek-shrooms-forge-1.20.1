package com.antonkuzmn1.xqwkeburenochekshrooms.client.itemstates.common;

import org.joml.Quaternionf;

public class ItemTransform {
    public float scale;
    public ItemVec3 translate;
    public Quaternionf rotate;

    public ItemTransform() {
        this.scale = 1.0f;
        this.translate = new ItemVec3(0f, 0f, 0f);
        this.rotate = toQuaternionf(0, 0, 0);
    }

    public ItemTransform(
            float scale,
            float translateX, float translateY, float translateZ
    ) {
        this.scale = scale;
        this.translate = new ItemVec3(translateX, translateY, translateZ);
        this.rotate = toQuaternionf(0, 0, 0);
    }

    public ItemTransform(
            float scale,
            float translateX, float translateY, float translateZ,
            float rotateX, float rotateY, float rotateZ
    ) {
        this.scale = scale;
        this.translate = new ItemVec3(translateX, translateY, translateZ);
        this.rotate = toQuaternionf(rotateX, rotateY, rotateZ);
    }

    public static Quaternionf toQuaternionf(float x, float y, float z) {
        return new Quaternionf().rotateXYZ(
                (float) Math.toRadians(x),
                (float) Math.toRadians(y),
                (float) Math.toRadians(z)
        );
    }
}
