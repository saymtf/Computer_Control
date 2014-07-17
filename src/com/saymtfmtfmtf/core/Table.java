package com.saymtfmtfmtf.core;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DragSource;
import java.io.IOException;

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
	protected Time[] time;
	protected Time_Remaining[] timeRemain;
	private String userType;
	
	/**
	 * Sets all the member vals empty
	 * Creates a new JTable and JScroll
	 */
	public Table() {
		time = new Time[3];
		timeRemain = new Time_Remaining[3];
	// Set empty vars
		for(int i = 0; i < 3; i++) {
			time[i] = new Time();
			timeRemain[i] = new Time_Remaining();
		}
		userType = "";
	
	// Create Initial Data Information
		Object[][] data = {
		        {"PC 001", "offline",
		         userType, time[0].getTime(), timeRemain[0].getTimeLeft(), time[0].getEndingTime()},
		        {"PC 002", "offline",
		        	userType, time[1].getTime(), timeRemain[1].getTimeLeft(), time[1].getEndingTime()},
		        {"PC 003", "offline",
		        	userType, time[2].getTime(), timeRemain[2].getTimeLeft(), time[2].getEndingTime()}
		    };
		this.data = data;
		
	// Creates Table
		jtable = new JTable(data, header);
		jtable.setEnabled(false);
		jtable.setPreferredScrollableViewportSize(new Dimension(400, 32));
	    jtable.setFillsViewportHeight(true);
	    jtable.setGridColor(new Color(150,150,150));
	    
	// Set DND
	    jtable.setTransferHandler(new TableColumnTransferHandler(jtable));
	//Creates Scroll Table
		scroll = new JScrollPane(jtable);
	}
	
	/**
	 * Updates all the info in the table 
	 */
	public void setJTable() {
		if(time[0].getEndingTime() != "None Set") {
			new Thread(time[0]).start();

			System.out.println(time[0].getTime());
		}else if(time[1].getEndingTime() != "None Set") {
			new Thread(time[1]).start();
		}else if(time[2].getEndingTime() != "None Set") {
			new Thread(time[2]).start();
		}
		
		/*
		 * Set An empty table
		 * Replaces old data with temp
		 */
		Object[][] temp = {
		        {"PC 001", "offline",
		         userType, time[0].getTime(), timeRemain[0].getTimeLeft(), time[0].getEndingTime()},
		        {"PC 002", "offline",
		        	userType, time[1].getTime(), timeRemain[1].getTimeLeft(), time[1].getEndingTime()},
		        {"PC 003", "offline",
		        	userType, time[2].getTime(), timeRemain[2].getTimeLeft(), time[2].getEndingTime()}
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

		jtable.setTransferHandler(new TableColumnTransferHandler(jtable));	
		scroll = new JScrollPane(jtable); // sets the new scroll obj
	}
	
	public interface Reorderable {
		   public void reorder(int fromIndex, int toIndex);
	}

	/**
	 * 
	 * 
	 * @class TransferHandler
	 *
	 */
	@SuppressWarnings("serial")
	protected class TableColumnTransferHandler extends TransferHandler {
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
			if(!canImport(info)) {
					return false;
			}
			Transferable t = info.getTransferable();
			try {
				String data = (String) t.getTransferData(DataFlavor.stringFlavor);
				JTable.DropLocation tableLocation = (JTable.DropLocation) info.getDropLocation();
				jtable.setDropMode(DropMode.USE_SELECTION);

				
				// The Row
				int row = -1;
				switch(tableLocation.getRow()) {
					case 0: row = 0;
						break;
					case 1: row = 1;
						break;
					case 2: row = 2;
						break;
					default:
						break;
				}
				System.out.println(data + " " + row + " ");
				
				if(row != -1) {
					time[row].setEndTime(data.substring(0, 3));
				//timeRemain[row].setTimeLeft(data, time[row].getTime());
				}
				
			} catch (UnsupportedFlavorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return true;
		}
		
		@Override
		protected void exportDone(JComponent c, Transferable t, int act) {
			if(act == TransferHandler.NONE) {
				table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
	}
	
}
