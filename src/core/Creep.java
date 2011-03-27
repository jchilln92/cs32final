package src.core;

import src.ui.IDrawableCreep;

public class Creep implements IDrawableCreep {
	public enum Type {
		GENERIC
	}

	public Type getType() {
		// TODO: stub
		return Type.GENERIC;
	}

	public double getHealthFraction() {
		// TODO: stub
		return 1;
	}
}
