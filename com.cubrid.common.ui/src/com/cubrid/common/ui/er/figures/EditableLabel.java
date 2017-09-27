package com.cubrid.common.ui.er.figures;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

import com.cubrid.common.ui.spi.ResourceManager;

public class EditableLabel extends
Label {
	private boolean isHeader = false;
	private boolean isPK = false;
	private boolean selected;
	public static int HEADER_LABEL_HEIGHT = 35;
	public static String HEADER_FONT_KEY = "ERD_TABLT_HEADER_FONT";

	private static Font protrudeFont = ResourceManager.getFont(ColumnsFigure.COLUMN_FONT_KEY,
			ColumnsFigure.DEFAULT_FONT_HEIGHT, ColumnsFigure.PROTRUDE_FONT_STYLE);
	private static Font nomalFont = ResourceManager.getFont(ColumnsFigure.COLUMN_FONT_KEY,
			ColumnsFigure.DEFAULT_FONT_HEIGHT, ColumnsFigure.DEFAULT_FONT_STYLE);

	public EditableLabel(String text) {
		super(text);
		this.setFont(nomalFont);
	}

	public EditableLabel(String text, Image image) {
		super(text, image);
	}

	private Rectangle getSelectionRectangle() {
		Rectangle bounds = getTextBounds().getCopy();
		bounds.expand(new Insets(2, 2, 0, 0));
		translateToParent(bounds);
		bounds.intersect(getBounds());
		return bounds;
	}

	/**
	 * If isBold is true, set a bold style font to the column figure.
	 * 
	 * @param isBold
	 */
	public void setFontProtrude(boolean isBold) {
		if (isProtrude() ^ (!isBold)) {
			return;
		}
		if (isBold) {
			setFont(protrudeFont);
		} else {
			setFont(nomalFont);
		}
	}

	public boolean isProtrude() {
		return getFont().equals(protrudeFont);
	}

	/**
	 * sets the text of the label
	 */
	@Override
	public void setText(String s) {
		super.setText(s);
	}

	/**
	 * sets the image of the label
	 */
	@Override
	public void setIcon(Image image) {
		super.setIcon(image);
	}

	@Override
	protected void paintFigure(Graphics graphics) {
		if (selected) {
			graphics.pushState();
			graphics.setBackgroundColor(ColorConstants.menuBackgroundSelected);
			graphics.fillRectangle(getSelectionRectangle());
			graphics.popState();
			graphics.setForegroundColor(ColorConstants.white);
		}
		super.paintFigure(graphics);
	}

	class TableHeaderTextBorder extends
	AbstractBorder {
		private final Insets insets = new Insets(5, 0, 5, 0);
		private Figure figure;

		protected TableHeaderTextBorder(Figure figure) {
			this.figure = figure;
		}

		public Insets getInsets(IFigure figure) {
			return insets;
		}

		public void paint(IFigure figure, Graphics graphics, Insets insets) {
		}
	}

	public void setSelected(boolean b) {
		selected = b;
	}

	public boolean isHeader() {
		return isHeader;
	}

	public void setHeader(boolean isHeader) {
		this.isHeader = isHeader;
		this.setBorder(new TableHeaderTextBorder(this));
	}

	public boolean isPK() {
		return isPK;
	}

	public void setPK(boolean isPK) {
		this.isPK = isPK;
	}

	public boolean isSelected() {
		return selected;
	}
}
