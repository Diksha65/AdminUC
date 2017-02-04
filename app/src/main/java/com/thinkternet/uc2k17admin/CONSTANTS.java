package com.thinkternet.uc2k17admin;

/**
 * Created by diksha on 29/1/17.
 */
public class CONSTANTS {

    private CONSTANTS(){}

    public static class EXTRAS {
        public static final String PLAYER_ID            = "PLAYER_ID";
        public static final String IMEI_NUMBER          = "IMEI_NUMBER";
        public static final String PHONE_TEAM           = "PHONE_TEAM";
        public static final String IS_FIRST_PLAYER      = "IS_FIRST_PLAYER";
    }

    public static class GEOQUERY {
        public static final String INITIAL              = "INITIAL";
        public static final String WAR_MAP              = "WAR_MAP";
        public static final String ADMIN                = "ADMIN";
        public static final String DELEGATED            = "DELEGATED";
    }

    public static class FIREBASE {
        /**
         *             First Layer
         */
        public static final String TEAMS                = "TEAMS";
        public static final String GEOFIRE              = "GEOFIRE";
        public static final String AVAILABLE_LOCATIONS  = "AVAILABLE_LOCATIONS";
        public static final String RESERVED_TEAMS       = "RESERVED_TEAMS";
        public static final String REGISTERED_PHONES    = "REGISTERED_PHONES";
        public static final String PLAYERS              = "PLAYERS";
        public static final String PHONE_PID            = "PHONE_PID";

        /**
         *              Rest Normal
         */

        public static final String COUNTER              = "COUNTER";
        public static final String DISPLAY_NAME         = "DISPLAY_NAME";
        public static final String TEAM_DUMMY           = "TEAM_DUMMY";
        public static final String TEAM_ID              = "TEAM_ID";
        public static final String PLAYER_ID            = "PLAYER_ID";
        public static final String LOCK                 = "LOCK";

    }

    public static class SHARED_PREF {
        public static final String FILE         = "urban_crusade_2k17_preferences";
        public static final String IMEI_NUMBER  = "IMEI_NUMBER";
    }
}