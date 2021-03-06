/*
 * Geometric location information. 
 */

package com.ceng316.model.metafile;

public class Location {
	private Double x;
	private Double y;
	public Location(Double x, Double y) {
		super();
		this.x = x;
		this.y = y;
	}
	public Double getX() {
		return x;
	}
	public void setX(Double x) {
		this.x = x;
	}
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}
	
	@Override
	public String toString() {
		if ( x == null || y == null){
			return "Unknown";
		}else
			return "X Coordinate :" + x + "Y Coordinate :" + y;
	}
	
}
