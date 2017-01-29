package com.example.diksha.admin;

import android.location.Location;
import android.support.design.widget.BottomSheetBehavior;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.google.android.gms.maps.GoogleMap;
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
        geoFire = new GeoFire(firebase.child(CONSTANTS.FIREBASE.GEOFIRE));
    }

    final GeoLocation[] getGeoLocations() { return geoLocations; }

    double querySize = 0.01; //KM
    GoogleMap googleMap;
    DatabaseReference firebase;
    GeoQuery geoQuery;
    GeoFire geoFire;
    Location playerLocation;
    BottomSheetBehavior bottomSheetBehavior;

    /*
            All Team Bases
     */

    private GeoLocation geoLocations[] = new GeoLocation[]{
            new GeoLocation(22.252201, 84.904448),
            new GeoLocation(22.252119, 84.904432),
            new GeoLocation(22.252178, 84.904555),
            new GeoLocation(22.252156, 84.904480),
            new GeoLocation(22.252138, 84.904555),
            new GeoLocation(22.252109, 84.904368),
            new GeoLocation(22.252104, 84.904633),
            new GeoLocation(22.252046, 84.904419),
            new GeoLocation(22.252051, 84.904550),
            new GeoLocation(22.252531, 84.901797),
            new GeoLocation(22.252530, 84.901692),
            new GeoLocation(22.252609, 84.901641),
            new GeoLocation(22.252702, 84.901767),
            new GeoLocation(22.252707, 84.901980),
            new GeoLocation(22.252681, 84.901737),
            new GeoLocation(22.252543, 84.901716),
            new GeoLocation(22.252589, 84.901850),
            new GeoLocation(22.252517, 84.901637),
            new GeoLocation(22.252392, 84.901361),
            new GeoLocation(22.252472, 84.901155),
            new GeoLocation(22.252522, 84.901255),
            new GeoLocation(22.252444, 84.901372),
            new GeoLocation(22.252532, 84.901439),
            new GeoLocation(22.252545, 84.901330),
            new GeoLocation(22.252412, 84.900992),
            new GeoLocation(22.252494, 84.901006),
            new GeoLocation(22.252395, 84.901126),
            new GeoLocation(22.253089, 84.903890),
            new GeoLocation(22.251671, 84.900255),
            new GeoLocation(22.252021, 84.900127),
            new GeoLocation(22.252211, 84.900520),
            new GeoLocation(22.251359, 84.901015),
            new GeoLocation(22.251191, 84.900477),
            new GeoLocation(22.252325, 84.900183),
            new GeoLocation(22.252325, 84.901032),
            new GeoLocation(22.251440, 84.901227),
            new GeoLocation(22.254547, 84.905273),
            new GeoLocation(22.254500, 84.905092),
            new GeoLocation(22.254270, 84.905044),
            new GeoLocation(22.254351, 84.905689),
            new GeoLocation(22.254035, 84.904914),
            new GeoLocation(22.254119, 84.905265),
            new GeoLocation(22.253955, 84.904879),
            new GeoLocation(22.254735, 84.904902),
            new GeoLocation(22.254506, 84.905812),
            new GeoLocation(22.253532, 84.902245),
            new GeoLocation(22.253496, 84.902362),
            new GeoLocation(22.253449, 84.902495),
            new GeoLocation(22.253373, 84.902730),
            new GeoLocation(22.252920, 84.902039),
            new GeoLocation(22.253169, 84.902115),
            new GeoLocation(22.253423, 84.902204),
            new GeoLocation(22.253080, 84.902278),
            new GeoLocation(22.253148, 84.902558),
            new GeoLocation(22.253565, 84.903403),
            new GeoLocation(22.253410, 84.903461),
            new GeoLocation(22.253263, 84.903533),
            new GeoLocation(22.253449, 84.903960),
            new GeoLocation(22.253555, 84.904247),
            new GeoLocation(22.253191, 84.903274),
            new GeoLocation(22.253163, 84.902716),
            new GeoLocation(22.253016, 84.903628),
            new GeoLocation(22.253089, 84.903890),
            new GeoLocation(22.252729,84.8999898),
            new GeoLocation(22.252706,84.9000758),
            new GeoLocation(22.252701,84.8998568),
            new GeoLocation(22.252753,84.8998788),
            new GeoLocation(22.252615,84.8999408),
            new GeoLocation(22.252498,84.9002488),
            new GeoLocation(22.252645,84.9000658),
            new GeoLocation(22.252616,84.8999568),
            new GeoLocation(22.2527304,84.900394),
            new GeoLocation(22.251935,84.8992108),
            new GeoLocation(22.251904,84.8993958),
            new GeoLocation(22.2520961,84.8998375),
            new GeoLocation(22.252208,84.8992298),
            new GeoLocation(22.252043,84.8995538),
            new GeoLocation(22.252029,84.8984408),
            new GeoLocation(22.252197,84.8984038),
            new GeoLocation(22.252382,84.8987038),
            new GeoLocation(22.252242,84.8983458),
            new GeoLocation(22.250425,84.9006898),
            new GeoLocation(22.250197,84.9006968),
            new GeoLocation(22.250259,84.9004888),
            new GeoLocation(22.250251,84.9006038),
            new GeoLocation(22.249983,84.9006068),
            new GeoLocation(22.249983,84.9006068),
            new GeoLocation(22.250323,84.9006358),
            new GeoLocation(22.250024,84.9007168),
            new GeoLocation(22.250233,84.9004748),
            new GeoLocation(22.253433,84.9006158),
            new GeoLocation(22.253356,84.9003588),
            new GeoLocation(22.253505,84.9005218),
            new GeoLocation(22.253534,84.9007068),
            new GeoLocation(22.253581,84.9004218),
            new GeoLocation(22.253665,84.9006658),
            new GeoLocation(22.253779,84.9006048),
            new GeoLocation(22.253563,84.9003208),
            new GeoLocation(22.253665,84.9004978)
    };

}
