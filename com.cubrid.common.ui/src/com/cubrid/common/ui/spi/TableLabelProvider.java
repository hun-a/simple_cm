package com.cubrid.common.ui.spi;

import static com.cubrid.common.core.util.NoOp.noOp;

import java.util.Map;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class TableLabelProvider implements
ITableLabelProvider {

	/**
	 * Returns the label image for the given column of the given element.
	 *
	 * @param element the object representing the entire row, or
	 *        <code>null</code> indicating that no input object is set in the
	 *        viewer
	 * @param columnIndex the zero-based index of the column in which the label
	 *        appears
	 * @return Image or <code>null</code> if there is no image for the given
	 *         object at columnIndex
	 */
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	/**
	 * Returns the label text for the given column of the given element.
	 *
	 * @param element the object representing the entire row, or
	 *        <code>null</code> indicating that no input object is set in the
	 *        viewer
	 * @param columnIndex the zero-based index of the column in which the label
	 *        appears
	 * @return String or or <code>null</code> if there is no text for the given
	 *         object at columnIndex
	 */
	@SuppressWarnings("unchecked")
	public String getColumnText(Object element, int columnIndex) {
		if (!(element instanceof Map)) {
			return "";
		}
		Map<String, Object> map = (Map<String, Object>) element;
		Object obj = map.get("" + columnIndex);
		return obj == null ? "" : obj.toString();
	}

	/**
	 * Returns whether the label would be affected by a change to the given
	 * property of the given element. This can be used to optimize a
	 * non-structural viewer update. If the property mentioned in the update
	 * does not affect the label, then the viewer need not update the label.
	 *
	 * @param element the element
	 * @param property the property
	 * @return <code>true</code> if the label would be affected, and
	 *         <code>false</code> if it would be unaffected
	 */
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	/**
	 * Disposes of this label provider. When a label provider is attached to a
	 * viewer, the viewer will automatically call this method when the viewer is
	 * being closed. When label providers are used outside of the context of a
	 * viewer, it is the client's responsibility to ensure that this method is
	 * called when the provider is no longer needed.
	 */
	public void dispose() {
		noOp();
	}

	/**
	 * Adds a listener to this label provider. Has no effect if an identical
	 * listener is already registered.
	 * <p>
	 * Label provider listeners are informed about state changes that affect the
	 * rendering of the viewer that uses this label provider.
	 * </p>
	 *
	 * @param listener a label provider listener
	 */
	public void addListener(ILabelProviderListener listener) {
		noOp();
	}

	/**
	 * Removes a listener to this label provider. Has no affect if an identical
	 * listener is not registered.
	 *
	 * @param listener a label provider listener
	 */
	public void removeListener(ILabelProviderListener listener) {
		noOp();
	}
}
