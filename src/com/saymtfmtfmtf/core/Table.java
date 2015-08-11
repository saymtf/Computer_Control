package com.saymtfmtfmtf.core;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DragSource;

import javax.activation.DataHandler;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.TransferHandler;

public class Table implements Runnable {
	private String[] header = { "PC", "Status", "User", "Start", "Time", "End" };
	private Object[][] data;
	private JTable jtable;
	private JScrollPane scroll;
	private Time time;
	private Time_Remaining timeRemain;
	private String userType;
	
	/**
	 * Sets all the member vals empty
	 * Creates a new JTable and JScroll
	 */
	public Table() {
	
	// Set empty vars
		time = new Time();
		timeRemain = new Time_Remaining();
		userType = "";
		
	// Create Initial Data Information
		Object[][] data = {
			        {"PC 001", "offline",
			         userType, time.getTime(), timeRemain.getTimeLeft(), time.getEndingTime()},
			        {"PC 002", "offline",
			        	userType, time.getTime(), timeRemain.getTimeLeft(), time.getEndingTime()},
			        {"PC 003", "offline",
			        	userType, time.getTime(), timeRemain.getTimeLeft(), time.getEndingTime()}
			    };
		this.data = data;
		
	// Creates Table
		jtable = new JTable(data, header);
		jtable.setEnabled(false);
		jtable.setPreferredScrollableViewportSize(new Dimension(400, 32));
	    jtable.setFillsViewportHeight(true);
	    jtable.setGridColor(new Color(150,150,150));
	    
	// Set DND
	    jtable.setDragEnabled(true);
	    jtable.setDropMode(DropMode.ON_OR_INSERT_COLS);
	    jtable.setTransferHandler(new TableColumnTransferHandler(jtable));
		
	//Creates Scroll Table
		scroll = new JScrollPane(jtable);
	}
	
	/**
	 * Updates all the info in the table 
	 */
	public void setJTable() {
		/*
		 * Set An empty table
		 * Replaces old data with temp
		 */
		Object[][] temp = {
		        {"PC 001", "offline",
		         userType, time.getTime(), timeRemain.getTimeLeft(), time.getEndingTime()},
		        {"PC 002", "offline",
		        	userType, time.getTime(), timeRemain.getTimeLeft(), time.getEndingTime()},
		        {"PC 003", "offline",
		        	userType, time.getTime(), timeRemain.getTimeLeft(), time.getEndingTime()}
		    };
		data = temp;
		
		// Creates Table
			JTable table = new JTable(data, header);
			table.setEnabled(false);
			table.setPreferredScrollableViewportSize(new Dimension(400, 32));
			table.setFillsViewportHeight(true);
			table.setGridColor(new Color(150,150,150));

		jtable = table; // replace old table with new
		
	}
	
	
	public void setCompTable(String currTime, int timeLeft, String endTime, String user, int a) {
		
	}
	
	
	/**
	 * @return Allows user to retrieve the jtable
	 */
	public JTable getCompTable() { return jtable; }
	
	/**
	 * @return the scroll object of the JTable
	 */
	public JScrollPane compScroll() { return scroll; }
	
	/**
	 * Continuously runs to check for updates.
	 */
	public void run() {
		setJTable(); // resets the jtable

		jtable.setDragEnabled(false);
		jtable.setDropMode(DropMode.ON_OR_INSERT_COLS);
		jtable.setTransferHandler(new TableColumnTransferHandler(jtable));	
		scroll = new JScrollPane(jtable); // sets the new scroll obj
	}
	
	public interface Reorderable {
		   public void reorder(int fromIndex, int toIndex);
	}

	/**
	 * 
	 * 
	 * @author Thane
	 *
	 */
	@SuppressWarnings("serial")
	public class TableColumnTransferHandler extends TransferHandler {
		private final DataFlavor localObjectFlavor = new DataFlavor(Integer.class, "Integer Column Index");

		private JTable table = null;
		
		
		public TableColumnTransferHandler(JTable table) {
			this.table = table;
		}

		/*
		@Override
		protected Transferable createTransferable(JComponent c) {
			assert(c == table);
			return new DataHandler(new Integer(table.getSelectedRow()), localObjectFlavor.getMimeType());
		}

		
		@Override
		public boolean canImport(TransferHandler.TransferSupport info) {
			boolean b = info.getComponent() == table && info.isDrop() && info.isDataFlavorSupported(localObjectFlavor);
			table.setCursor(b ? DragSource.DefaultMoveDrop : DragSource.DefaultMoveNoDrop);
		    return b;
		}
		
		@Override
		public int getSourceActions(JComponent C) {
			return TransferHandler.COPY_OR_MOVE;
		}
		
		@Override
		public boolean importData(TransferHandler.TransferSupport info) {
			JTable target = (JTable) info.getComponent();
			JTable.DropLocation dl = (JTable.DropLocation) info.getDropLocation();
			int index = dl.getRow();

		*/
		@Override
		public boolean canImport(TransferHandler.TransferSupport info) {
			if(!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				return false;
			}
			
			JTable.DropLocation dl = (JTable.DropLocation)info.getDropLocation();
			if(dl.getColumn() != 4) {
				return false;
			}
			return true;
		}
		
		@Override
		public int getSourceActions(JComponent C) {
			return TransferHandler.COPY_OR_MOVE;
		}
		
		@Override
		public boolean importData(TransferHandler.TransferSupport info) {
			JTable target = (JTable) info.getComponent();
			JTable.DropLocation dl = (JTable.DropLocation) info.getDropLocation();
			int index = dl.getColumn();

			int max = table.getModel().getRowCount();
			if(index < 0 || index > max) { index = max; }
			
			target.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			
			try {
				int colFrom = Integer.parseInt((String) info.getTransferable().getTransferData(DataFlavor.stringFlavor));
				System.out.println(colFrom); // colFrom is the value from the right table.
				if(colFrom != -1 && colFrom != index) {
					((Reorderable)table.getModel()).reorder(colFrom, index);
					if(index > colFrom) { index--; }

					target.getSelectionModel().addSelectionInterval(index, index);
					return true;
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			return false;
		}
		
		@Override
		protected void exportDone(JComponent c, Transferable t, int act) {
			if(act == TransferHandler.NONE) {
				table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
	}
	
}
