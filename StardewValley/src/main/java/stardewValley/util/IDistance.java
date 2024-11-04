package stardewValley.util;

import stardewValley.model.Position;

@FunctionalInterface
public interface IDistance {
    double distance(Position from, Position to);
}
