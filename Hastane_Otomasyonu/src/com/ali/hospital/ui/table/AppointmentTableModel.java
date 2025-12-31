package com.ali.hospital.ui.table;

import com.ali.hospital.dto.AppointmentDTO;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class AppointmentTableModel extends AbstractTableModel {
    private final String[] cols = {"ID","Hasta Adı","Doktor Adı","Poliklinik","Tarih"};
    private List<AppointmentDTO> rows = new ArrayList<>();

    public void setRows(List<AppointmentDTO> rows){ this.rows = rows==null? new ArrayList<>():rows; fireTableDataChanged(); }

    @Override public int getRowCount(){ return rows.size(); }
    @Override public int getColumnCount(){ return cols.length; }
    @Override public String getColumnName(int c){ return cols[c]; }

    @Override
    public Object getValueAt(int r, int c) {
        AppointmentDTO x = rows.get(r);
        switch (c) {
            case 0: return x.id;
            case 1: return x.patientName;
            case 2: return x.doctorName;
            case 3: return x.clinicName;
            case 4: return x.dateTime;
            default: return "";
        }
    }
}
