package src.core.xml;

import java.awt.geom.Point2D;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

/**
 * A conversion class that allows the Point2D.Double class to be
 * written and read to/from XML.  We need this (as opposed to normal
 * annotations) because obviously we are not able to add the annotations
 * to the Point2D class (it is part of the Java library)
 */
public class PointConverter implements Converter<Point2D.Double> {
	@Override
	public Point2D.Double read(InputNode node) throws Exception {
		String x = node.getAttribute("x").getValue();
		String y = node.getAttribute("y").getValue();
		
		return new Point2D.Double(Double.parseDouble(x),
								  Double.parseDouble(y));
	}

	@Override
	public void write(OutputNode node, Point2D.Double point) throws Exception {
		String x = Double.toString(point.getX());
		String y = Double.toString(point.getY());
			
		node.setAttribute("x", x);
		node.setAttribute("y", y);
	}
}
