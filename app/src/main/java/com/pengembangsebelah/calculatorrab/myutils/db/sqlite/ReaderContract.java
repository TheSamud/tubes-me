package com.pengembangsebelah.calculatorrab.myutils.db.sqlite;

import android.provider.BaseColumns;

public class ReaderContract {
    public ReaderContract(){}
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME_PROJECT = "proje";
        public static final String TABLE_NAME_KOEFISIEN = "koefi";
        public static final String TABLE_KOEFISIEN = "koefisiensitok";
        public static final String TABLE_NAME_DATA_PROJECT = "dapro";

        public static final String COLUMN_NAME_PROJECT_NAME = "proname";
        public static final String COLUMN_NAME_LOCATION = "locpro";
        public static final String COLUMN_NAME_IDSKOEF = "ikof";


        public static final String COLUMN_NAME_PROJECT_NAME_KOEFISIEN = "pronamekof";
        public static final String COLUMN_NAME_PROJECT_KOEFISIEN_TOTAL = "total";
        public static final String COLUMN_NAME_PEKERJAAN = "pekerjaan";

        public static final String COLUMN_HARGA_PEKERJAAN = "harpekerjaan";

        public static final String COLUMN_ID_PEKERJAAN = "_id_pekerjaan";
        public static final String COLUMN_ID_PEKERJAAN2 = "_id_pekerjaan2";
        public static final String COLUMN_ID_PEKERJAAN3 = "_id_pekerjaanmain";
        public static final String COLUMN_ID_KOEFISIWN = "_id_koefisien";
        public static final String VALUE_DATA_PROJECT = "value_project";
    }
}
