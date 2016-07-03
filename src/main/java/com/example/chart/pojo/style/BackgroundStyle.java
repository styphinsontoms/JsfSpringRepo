package com.example.chart.pojo.style;

public class BackgroundStyle {

	public BackgroundStyle() {
		super();
		this.fillStyle = new FillStyle();
		this.cornerStyle = new CornerStyle();
		this.hatchFillStyle = new HatchFillStyle();
		this.strokeStyle = new StrokeStyle();
	}

	public BackgroundStyle(FillStyle fillStyle, StrokeStyle strokeStyle,
			CornerStyle cornerStyle, HatchFillStyle hatchFillStyle) {
		super();
		this.fillStyle = fillStyle;
		this.strokeStyle = strokeStyle;
		this.cornerStyle = cornerStyle;
		this.hatchFillStyle = hatchFillStyle;
	}

	private FillStyle fillStyle;
	private StrokeStyle strokeStyle;
	private CornerStyle cornerStyle;
	private HatchFillStyle hatchFillStyle;

	public FillStyle getFillStyle() {
		return fillStyle;
	}

	public void setFillStyle(FillStyle fillStyle) {
		this.fillStyle = fillStyle;
	}

	public StrokeStyle getStrokeStyle() {
		return strokeStyle;
	}

	public void setStrokeStyle(StrokeStyle strokeStyle) {
		this.strokeStyle = strokeStyle;
	}

	public CornerStyle getCornerStyle() {
		return cornerStyle;
	}

	public void setCornerStyle(CornerStyle cornerStyle) {
		this.cornerStyle = cornerStyle;
	}

	public HatchFillStyle getHatchFillStyle() {
		return hatchFillStyle;
	}

	public void setHatchFillStyle(HatchFillStyle hatchFillStyle) {
		this.hatchFillStyle = hatchFillStyle;
	}

}
