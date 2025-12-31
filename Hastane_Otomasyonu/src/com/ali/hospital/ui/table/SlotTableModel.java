package com.ali.hospital.ui.table;

import com.ali.hospital.dto.SlotDTO;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class SlotTableModel extends AbstractTableModel {
    private final String[] cols = {"ID","Tarih"};
    private List<SlotDTO> rows = new ArrayList<>();

    public void setRows(List<SlotDTO> rows){ this.rows = rows==null? new ArrayList<>():rows; fireTableDataChanged(); }
    public SlotDTO getAt(int i){ return rows.get(i); }

    @Override public int getRowCount(){ return rows.size(); }
    @Override public int getColumnCount(){ return cols.length; }
    @Override public String getColumnName(int c){ return cols[c]; }

    @Override
    public Object getValueAt(int r, int c) {
        SlotDTO x = rows.get(r);
        return c==0 ? x.id : x.dateTime;
    }
}
