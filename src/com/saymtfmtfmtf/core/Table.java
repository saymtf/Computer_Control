package com.saymtfmtfmtf.core;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DragSource;
import java.io.IOException;

import javax.activation.ActivationDataFlavor;
import javax.activation.DataHandler;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.TransferHandler;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Table implements Runnable{
	private String[] header = { "PC", "Status", "User", "Start", "Time", "End" };
	private Object[][] data;
	private JTable jtable;
	private TableModel dataModel;
	private JScrollPane scroll;
	private Time time;
	private Time_Remaining timeRemain;
	private String userType;
	private TableRowTransferHandler transferHandler = null;
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

		dataModel = new DefaultTableModel(data, header);
		jtable = new JTable(dataModel);
		jtable.setEnabled(false);
		jtable.setPreferredScrollableViewportSize(new Dimension(400, 32));
	    jtable.setFillsViewportHeight(true);
	    jtable.setGridColor(new Color(150,150,150));
	    
	// Set DND
		transferHandler = new TableRowTransferHandler(jtable);
	    jtable.setDragEnabled(true);
	    jtable.setDropMode(DropMode.INSERT_COLS);
	    jtable.setTransferHandler(transferHandler); 
		
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

		jtable.setDragEnabled(true);
		jtable.setDropMode(DropMode.INSERT_COLS);
		jtable.setTransferHandler(transferHandler);	
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
	public class TableRowTransferHandler extends TransferHandler {
		private final DataFlavor localObjectFlavor = new ActivationDataFlavor(Integer.class, "Integer Row Index");
		   private JTable           table             = null;
		   
		   
		   public TableRowTransferHandler(JTable table) {
		      this.table = table;
		   }

		   @Override
		   protected Transferable createTransferable(JComponent c) {
		      assert (c == table);
		      return new DataHandler(new Integer(table.getSelectedColumn()), DataFlavor.stringFlavor.getMimeType());
		   }

		   @Override
		   public boolean canImport(TransferHandler.TransferSupport info) {
			  // Checks if it is a string, and makes sure the drop is only on Column 4 (Time)
		      boolean b = info.isDataFlavorSupported(DataFlavor.stringFlavor) 
		    		  && ((JTable.DropLocation)info.getDropLocation()).getColumn() == 4;
		      
		      //System.out.println("Is it FALSE: " + b);
		      table.setCursor(b ? DragSource.DefaultCopyDrop : DragSource.DefaultCopyNoDrop);
		      return b;
		   }

		   @Override
		   public int getSourceActions(JComponent c) {
		      return TransferHandler.COPY_OR_MOVE;
		   }

		   @Override
		   public boolean importData(TransferSupport info) {
			   if (!info.isDrop()) { return false; }
			   
			   
			    //getDropLocation Returns the component of the drop
		        JTable.DropLocation dl = (JTable.DropLocation) info.getDropLocation();
		        int colIndex = dl.getColumn(); // the col of where user let go of mouse
		        int rowIndex = dl.getRow(); // the row of where user let go of mouse

			   // To query the data from the drop
			   Transferable t = info.getTransferable();
			   try {
				   // getTransferData gets the data's information and store it
				   String data = (String) t.getTransferData(DataFlavor.stringFlavor);
				   //System.out.println("The Amount of Time " + data);

				   // update the time in the table
				   Integer val = (Integer) dataModel.getValueAt(rowIndex, colIndex); //// ****************** AFTER 1 DROP - STOPS WORKING *************//

				   System.out.println(val);
				   if(val == -1) {
					   System.out.println("IF");
						dataModel.setValueAt(data, rowIndex, colIndex);	
				   }
					   
				   System.out.println("ELSE");
				   int timeVal = Integer.parseInt(data);
				   System.out.println(timeVal);
				   
				   
				   table.setModel(dataModel);
				   table.setBackground(new Color(100,200,240));

			        return true;
			   } catch (UnsupportedFlavorException e) {
				// TODO Auto-generated catch block
				   e.printStackTrace();
				   return false;
			   } catch (IOException e) {
				// TODO Auto-generated catch block
				   e.printStackTrace();
				   return false;
			   }
		   }

		   @Override
		   protected void exportDone(JComponent c, Transferable t, int act) {
		      if ((act == TransferHandler.COPY) || (act == TransferHandler.NONE)) {
		         table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		      }
		   }
	}
	
}
