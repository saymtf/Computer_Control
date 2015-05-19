package com.saymtfmtfmtf.core;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.DropMode;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Table implements Runnable {
	private String[] header = { "PC", "Status", "User", "Start", "Time", "End" };;
	private Object[][] data;
	private String startTime;
	private int timeLeft;
	private String endTime;
	private String userType;
	private int position;
	private JTable jtable;
	private JScrollPane scroll;
	
	/**
	 * Sets all the member vals empty
	 * Creates a new JTable and JScroll
	 */
	public Table() {
	
	// Set empty vars
		startTime = "";
		timeLeft = 0;
		endTime = "";
		userType = "";
		position = 0;
		
	// Create Initial Data Information
		Object[][] data = {
			        {"PC 001", "offline",
			         "user", new String(""), new String(""), new String("")},
			        {"PC 002", "offline",
			         "user", new String(""), new String(""), new String("")},
			        {"PC 003", "offline",
			         "user", new String(""), new String(""), new String("")}
			    };
		this.data = data;
		
	// Creates Table
		jtable = new JTable(data, header);
		
		jtable.setPreferredScrollableViewportSize(new Dimension(400, 32));
	    jtable.setFillsViewportHeight(true);
	    jtable.setGridColor(new Color(150,150,150));

	    
	// Set DND

	    jtable.setDragEnabled(true);
	    jtable.setDropMode(DropMode.ON_OR_INSERT_COLS);
	    jtable.setTransferHandler(new TableColumnTransferHandler(jtable));

	    //jtable.setTransferHandler(new TableColumnTransferHandler(jtable));
	    //jtable.setDropMode(DropMode.ON_OR_INSERT);

	//Creates Scroll Table
		scroll = new JScrollPane(jtable);
	}
	
	
	/**
	 * Changes the table for updated info
	 * 
	 * @param startTime
	 * @param timeLeft
	 * @param endTime
	 * @return table
	 */
	
	public void setCompTable(String startTime, int timeLeft, String endTime, String userType, int position) {
		this.startTime = startTime;
		this.timeLeft = timeLeft;
		this.endTime = endTime;
		this.userType = userType;
		this.position = position;
		setJTable();
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
		         "user", startTime, timeLeft, endTime},
		        {"PC 002", "offline",
		         "user", startTime, timeLeft, endTime},
		        {"PC 003", "offline",
		         "user", startTime, timeLeft, endTime}
		        };
		data = temp;
		
		// Creates a new Table and resets it
		JTable table = new JTable(temp, header);
		table.setPreferredScrollableViewportSize(new Dimension(400, 32));
	    table.setFillsViewportHeight(true);
	    
		table.setGridColor(new Color(150,150,150));
		
		jtable = table; // replace old table with new
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
<<<<<<< HEAD

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
<<<<<<< HEAD
	 * @author Thane
	 *
	 */
	@SuppressWarnings("serial")
	public class TableColumnTransferHandler extends TransferHandler {
		private final DataFlavor localObjectFlavor = new DataFlavor(Integer.class, "Integer Column Index");
=======
	 * @class TransferHandler
	 *
	 */
	@SuppressWarnings("serial")
	protected class TableColumnTransferHandler extends TransferHandler {
>>>>>>> FETCH_HEAD
		private JTable table = null;
		
		
		public TableColumnTransferHandler(JTable table) {
			this.table = table;
		}
<<<<<<< HEAD
		
=======
		/*
>>>>>>> FETCH_HEAD
		@Override
		protected Transferable createTransferable(JComponent c) {
			assert(c == table);
			return new DataHandler(new Integer(table.getSelectedRow()), localObjectFlavor.getMimeType());
		}
<<<<<<< HEAD
		
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
=======
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
>>>>>>> FETCH_HEAD
			int max = table.getModel().getRowCount();
			
			if(index < 0 || index > max) { index = max; }
			
			target.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			
			try {
<<<<<<< HEAD
				Integer rowFrom = (Integer) info.getTransferable().getTransferData(localObjectFlavor);
				if(rowFrom != -1 && rowFrom != index) {
					((Reorderable)table.getModel()).reorder(rowFrom, index);
					if(index > rowFrom) { index--; }
=======
				Integer colFrom = (Integer) info.getTransferable().getTransferData(DataFlavor.stringFlavor);
				if(colFrom != -1 && colFrom != index) {
					((Reorderable)table.getModel()).reorder(colFrom, index);
					if(index > colFrom) { index--; }
>>>>>>> FETCH_HEAD
					target.getSelectionModel().addSelectionInterval(index, index);
					return true;
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			return false;
		}
=======
		
		scroll = new JScrollPane(jtable); // sets the new scroll obj
>>>>>>> parent of cf37021... New Update
		
		//System.out.println("Table Run" + startTime); // DEBUG
	}
}
