package com.thinkternet.uc2k17admin;

import android.location.Location;
import android.support.design.widget.BottomSheetBehavior;

import com.firebase.geofire.GeoQuery;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by diksha on 29/1/17.
 */
class DataStash {

    private static DataStash sDataStash;

    static DataStash getsDataStash() {
        if(sDataStash == null)
            sDataStash = new DataStash();
        return sDataStash;
    }

    private DataStash() {
        firebase = FirebaseDatabase.getInstance().getReference();
    }

    final LatLng[] getLatLngs() { return LatLngs; }

    LatLng getLatLng(){
        return new LatLng(22.252395, 84.901126);
    }


    String getString(LatLng latLng){
        return "" + latLng.latitude + ", " + latLng.longitude;
    }

    double querySize = 0.01; //KM
    GoogleMap googleMap;
    DatabaseReference firebase;
    GeoQuery geoQuery;
    Location playerLocation;
    BottomSheetBehavior bottomSheetBehavior;

    /*
            All Team Bases
     */

    public final LatLng LatLngs[] = new LatLng[]{
            new LatLng(22.252107, 84.904425),
            new LatLng(22.252024, 84.904715),
            new LatLng(22.251927, 84.905058),
            new LatLng(22.251904, 84.904374),
            new LatLng(22.251715, 84.904331),
            new LatLng(22.251819, 84.904680),
            new LatLng(22.252158, 84.904625),
            new LatLng(22.252192, 84.904830),
            new LatLng(22.252311, 84.904929),
            new LatLng(22.252076, 84.904057),
            new LatLng(22.252609, 84.901641),
            new LatLng(22.254270, 84.905044),
            new LatLng(22.253410, 84.903461),
            new LatLng(22.253263, 84.903533),
            new LatLng(22.252530, 84.901770),
            new LatLng(22.253263, 84.903533),
            new LatLng(22.254039, 84.901581),
            new LatLng(22.252545, 84.901524),
            new LatLng(22.253449, 84.903960),
            new LatLng(22.254224, 84.904794),
            new LatLng(22.254357, 84.901468),
            new LatLng(22.252609, 84.901841),
            new LatLng(22.253402, 84.904149),
            new LatLng(22.25424, 84.90122),
            new LatLng(22.25396, 84.90138),
            new LatLng(22.253191, 84.903274),
            new LatLng(22.25407, 84.90123),
            new LatLng(22.25418, 84.90145),
            new LatLng(22.253163, 84.902716),
            new LatLng(22.252732, 84.901995),
            new LatLng(22.25441, 84.90119),
            new LatLng(22.2545, 84.90134),
            new LatLng(22.254119, 84.905265),
            new LatLng(22.253016, 84.903628),
            new LatLng(22.253955, 84.904879),
            new LatLng(22.253673, 84.901205),
            new LatLng(22.254175, 84.900932),
            new LatLng(22.254735, 84.904902),
            new LatLng(22.253784, 84.900886),
            new LatLng(22.252503, 84.902201),
            new LatLng(22.253088, 84.902899),
            new LatLng(22.252818, 84.902776),
            new LatLng(22.253394, 84.904325),
            new LatLng(22.253497, 84.900967),
            new LatLng(22.253537, 84.901329),
            new LatLng(22.252809, 84.903296),
            new LatLng(22.251436, 84.900365),
            new LatLng(22.253103, 84.903908),
            new LatLng(22.254146, 84.905167),
            new LatLng(22.253230, 84.904407),
            new LatLng(22.252959, 84.904540),
            new LatLng(22.252011, 84.900094),
            new LatLng(22.251825, 84.900187),
            new LatLng(22.251292, 84.900838),
            new LatLng(22.250649, 84.900697),
            new LatLng(22.251430, 84.901234),
            new LatLng(22.250413, 84.900780),
            new LatLng(22.255768, 84.903947),
            new LatLng(22.25014, 84.90088),
            new LatLng(22.25234, 84.90162),
            new LatLng(22.25523, 84.90416),
            new LatLng(22.25161, 84.90167),
            new LatLng(22.25468, 84.90439),
            new LatLng(22.25101, 84.90053),
            new LatLng(22.25005, 84.90121),
            new LatLng(22.2506, 84.90185),
            new LatLng(22.25453, 84.90488),
            new LatLng(22.25334, 84.90495),
            new LatLng(22.2512, 84.90113),
            new LatLng(22.25277, 84.90519),
            new LatLng(22.25263, 84.90009),
            new LatLng(22.25451, 84.90065),
            new LatLng(22.25248, 84.90051),
            new LatLng(22.25243, 84.90017),
            new LatLng(22.2524, 84.90076),
            new LatLng(22.25222, 84.90052),
            new LatLng(22.25253, 84.90342),
            new LatLng(22.25368, 84.90458),
            new LatLng(22.25299, 84.90027),
            new LatLng(22.25333, 84.9006),
            new LatLng(22.25477, 84.90098),
            new LatLng(22.25323, 84.90246),
            new LatLng(22.25495, 84.90145),
            new LatLng(22.25308, 84.90245),
            new LatLng(22.25286, 84.90234),
            new LatLng(22.25256, 84.89966),
            new LatLng(22.25515, 84.90204),
            new LatLng(22.25262, 84.90255),
            new LatLng(22.25539, 84.90275),
            new LatLng(22.25238, 84.89923),
            new LatLng(22.25218, 84.89895),
            new LatLng(22.25196, 84.89903),
            new LatLng(22.25564, 84.90339),
            new LatLng(22.25178, 84.90227),
            new LatLng(22.25261, 84.90475),
            new LatLng(22.25373, 84.90424),
            new LatLng(22.25173, 84.90209),
            new LatLng(22.25297, 84.90417),
            new LatLng(22.25301, 84.90071),
            new LatLng(22.25211, 84.90521),
            new LatLng(22.25231, 84.9035),
            new LatLng(22.25243, 84.90117),
            new LatLng(22.252161, 84.903672),
            new LatLng(22.253705, 84.902492),
            new LatLng(22.253749, 84.901959),
            new LatLng(22.255014, 84.903782),
            new LatLng(22.254783, 84.903151),
            new LatLng(22.250482, 84.901559),
            new LatLng(22.250301, 84.901805),
            new LatLng(22.250379, 84.901237),
            new LatLng(22.250323, 84.901530),
            new LatLng(22.250491, 84.902026),
            new LatLng(22.253487, 84.902568),
            new LatLng(22.252046, 84.899564),
            new LatLng(22.252104, 84.89984)
    };
}
