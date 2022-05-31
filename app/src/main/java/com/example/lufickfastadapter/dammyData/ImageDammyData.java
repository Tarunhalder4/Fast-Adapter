package com.example.lufickfastadapter.dammyData;

import android.content.Context;

import com.example.lufickfastadapter.R;
import com.example.lufickfastadapter.drawer.SimpleImageItem;
import com.example.lufickfastadapter.items.ImageItem;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ImageDammyData {

    Context context;

    public ImageDammyData(Context context) {
        this.context = context;
    }

    public List<ImageItem> getImageItems() {
        return toList(
                new ImageItem(context).setHeartImage(R.drawable.wight_heart_broken_24).setCountryName("Yellowstone United States").setDescription("#100017").setImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/yellowstone-united_states-17.jpg"),
                new ImageItem(context).setHeartImage(R.drawable.wight_heart_broken_24).setCountryName("Victoria Australia").setDescription("#100031").setImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/victoria-australia-31.jpg"),
                new ImageItem(context).setHeartImage(R.drawable.wight_heart_broken_24).setCountryName("Valencia Spain").setDescription("#100082").setImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/valencia-spain-82.jpg"),
                new ImageItem(context).setHeartImage(R.drawable.wight_heart_broken_24).setCountryName("Xigaze, Tibet China").setCountryName("#100030").setImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/xigaze,_tibet-china-30.jpg"),
                new ImageItem(context).setHeartImage(R.drawable.wight_heart_broken_24).setCountryName("Utah United States").setCountryName("#100096").setImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/utah-united_states-96.jpg"),
                new ImageItem(context).setHeartImage(R.drawable.wight_heart_broken_24).setCountryName("Utah United States").setCountryName("#100015").setImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/utah-united_states-15.jpg"),
                new ImageItem(context).setHeartImage(R.drawable.wight_heart_broken_24).setCountryName("Utah Un.setCountryName").setCountryName("#100088").setImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/utah-united_states-88.jpg"),
                new ImageItem(context).setHeartImage(R.drawable.wight_heart_broken_24).setCountryName("Umm Al Quwain United Arab Emirates").setCountryName("#100013").setImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/umm_al_quwain-united_arab_emirates-13.jpg"),
                new ImageItem(context).setHeartImage(R.drawable.wight_heart_broken_24).setCountryName("Texas United States").setCountryName("#100026").setImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/texas-united_states-26.jpg"),
                new ImageItem(context).setHeartImage(R.drawable.wight_heart_broken_24).setCountryName("Siuslaw National Forest United States").setCountryName("#100092").setImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/siuslaw_national_forest-united_states-92.jpg"),
                new ImageItem(context).setHeartImage(R.drawable.wight_heart_broken_24).setCountryName("The Minquiers Channel Islands").setCountryName("#100069").setImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/the_minquiers-channel_islands-69.jpg"),
                new ImageItem(context).setHeartImage(R.drawable.wight_heart_broken_24).setCountryName("Texas United States").setCountryName("#100084").setImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/texas-united_states-84.jpg"),
                new ImageItem(context).setHeartImage(R.drawable.wight_heart_broken_24).setCountryName("Tabuaeran Kiribati").setCountryName("#100050").setImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/tabuaeran-kiribati-50.jpg"),
                new ImageItem(context).setHeartImage(R.drawable.wight_heart_broken_24).setCountryName("Stanislaus River United States").setCountryName("#100061").setImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/stanislaus_river-united_states-61.jpg"),
                new ImageItem(context).setHeartImage(R.drawable.wight_heart_broken_24).setCountryName("S?ehitkamil Turkey").setCountryName("#100072").setImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/s?ehitkamil-turkey-72.jpg"),
                new ImageItem(context).setHeartImage(R.drawable.wight_heart_broken_24).setCountryName("Salinas Grandes Argentina").setCountryName("#100025").setImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/salinas_grandes-argentina-25.jpg"),
                new ImageItem(context).setHeartImage(R.drawable.wight_heart_broken_24).setCountryName("Shadegan Refuge Iran").setCountryName("#100012").setImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/shadegan_refuge-iran-12.jpg"),
                new ImageItem(context).setHeartImage(R.drawable.wight_heart_broken_24).setCountryName("San Pedro De Atacama Chile").setCountryName("#100043").setImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/san_pedro_de_atacama-chile-43.jpg"),
                new ImageItem(context).setHeartImage(R.drawable.wight_heart_broken_24).setCountryName("Ragged Island The Bahamas").setCountryName("#100064").setImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/ragged_island-the_bahamas-64.jpg"),
                new ImageItem(context).setHeartImage(R.drawable.wight_heart_broken_24).setCountryName("Qinghai Lake China").setCountryName("#100080").setImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/qinghai_lake-china-80.jpg"),
                new ImageItem(context).setHeartImage(R.drawable.wight_heart_broken_24).setCountryName("Qesm Al Wahat Ad Dakhlah Egypt").setCountryName("#100056").setImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/qesm_al_wahat_ad_dakhlah-egypt-56.jpg"),
                new ImageItem(context).setHeartImage(R.drawable.wight_heart_broken_24).setCountryName("Riedstadt Germany").setCountryName("#100042").setImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/riedstadt-germany-42.jpg"),
                new ImageItem(context).setHeartImage(R.drawable.wight_heart_broken_24).setCountryName("Redwood City United States").setCountryName("#100048").setImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/redwood_city-united_states-48.jpg"),
                new ImageItem(context).setHeartImage(R.drawable.wight_heart_broken_24).setCountryName("Nyingchi, Tibet China").setCountryName("#100098").setImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/nyingchi,_tibet-china-98.jpg"),
                new ImageItem(context).setHeartImage(R.drawable.wight_heart_broken_24).setCountryName("Ngari, Tibet China").setCountryName("#100057").setImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/ngari,_tibet-china-57.jpg"),
                new ImageItem(context).setHeartImage(R.drawable.wight_heart_broken_24).setCountryName("Pozoantiguo Spain").setCountryName("#100099").setImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/pozoantiguo-spain-99.jpg"),
                new ImageItem(context).setHeartImage(R.drawable.wight_heart_broken_24).setCountryName("Ningaloo Australia").setCountryName("#100073").setImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/ningaloo-australia-73.jpg"),
                new ImageItem(context).setHeartImage(R.drawable.wight_heart_broken_24).setCountryName("Niederzier Germany").setCountryName("#100079").setImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/niederzier-germany-79.jpg"),
                new ImageItem(context).setHeartImage(R.drawable.wight_heart_broken_24).setCountryName("Olympic Dam Australia").setCountryName("#100065").setImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/olympic_dam-australia-65.jpg"),
                new ImageItem(context).setHeartImage(R.drawable.wight_heart_broken_24).setCountryName("Peedamulla Australia").setCountryName("#100040").setImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/peedamulla-australia-40.jpg"),
                new ImageItem(context).setHeartImage(R.drawable.wight_heart_broken_24).setCountryName("Nevado Tres Cruces Park Chile").setCountryName("#100089").setImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/nevado_tres_cruces_park-chile-89.jpg")
        );
    }

    public static List<SimpleImageItem> getSimpleImageItems() {
        return toList(
                new SimpleImageItem().withIdentifier(1L).withName("Yang Zhuo Yong Cuo, Tibet China").withDescription("#100063").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/yang_zhuo_yong_cuo,_tibet-china-63.jpg"),
                new SimpleImageItem().withIdentifier(2L).withName("Yellowstone United States").withDescription("#100017").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/yellowstone-united_states-17.jpg"),
                new SimpleImageItem().withIdentifier(3L).withName("Victoria Australia").withDescription("#100031").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/victoria-australia-31.jpg"),
                new SimpleImageItem().withIdentifier(4L).withName("Valencia Spain").withDescription("#100082").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/valencia-spain-82.jpg"),
                new SimpleImageItem().withIdentifier(5L).withName("Xigaze, Tibet China").withDescription("#100030").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/xigaze,_tibet-china-30.jpg"),
                new SimpleImageItem().withIdentifier(6L).withName("Utah United States").withDescription("#100096").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/utah-united_states-96.jpg"),
                new SimpleImageItem().withIdentifier(7L).withName("Utah United States").withDescription("#100015").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/utah-united_states-15.jpg"),
                new SimpleImageItem().withIdentifier(8L).withName("Utah United States").withDescription("#100088").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/utah-united_states-88.jpg"),
                new SimpleImageItem().withIdentifier(9L).withName("Umm Al Quwain United Arab Emirates").withDescription("#100013").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/umm_al_quwain-united_arab_emirates-13.jpg"),
                new SimpleImageItem().withIdentifier(10L).withName("Texas United States").withDescription("#100026").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/texas-united_states-26.jpg"),
                new SimpleImageItem().withIdentifier(11L).withName("Siuslaw National Forest United States").withDescription("#100092").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/siuslaw_national_forest-united_states-92.jpg"),
                new SimpleImageItem().withIdentifier(12L).withName("The Minquiers Channel Islands").withDescription("#100069").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/the_minquiers-channel_islands-69.jpg"),
                new SimpleImageItem().withIdentifier(13L).withName("Texas United States").withDescription("#100084").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/texas-united_states-84.jpg"),
                new SimpleImageItem().withIdentifier(14L).withName("Tabuaeran Kiribati").withDescription("#100050").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/tabuaeran-kiribati-50.jpg"),
                new SimpleImageItem().withIdentifier(15L).withName("Stanislaus River United States").withDescription("#100061").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/stanislaus_river-united_states-61.jpg"),
                new SimpleImageItem().withIdentifier(16L).withName("Salinas Grandes Argentina").withDescription("#100025").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/salinas_grandes-argentina-25.jpg"),
                new SimpleImageItem().withIdentifier(17L).withName("Shadegan Refuge Iran").withDescription("#100012").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/shadegan_refuge-iran-12.jpg"),
                new SimpleImageItem().withIdentifier(18L).withName("San Pedro De Atacama Chile").withDescription("#100043").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/san_pedro_de_atacama-chile-43.jpg"),
                new SimpleImageItem().withIdentifier(19L).withName("Ragged Island The Bahamas").withDescription("#100064").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/ragged_island-the_bahamas-64.jpg"),
                new SimpleImageItem().withIdentifier(20L).withName("Qinghai Lake China").withDescription("#100080").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/qinghai_lake-china-80.jpg"),
                new SimpleImageItem().withIdentifier(21L).withName("Qesm Al Wahat Ad Dakhlah Egypt").withDescription("#100056").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/qesm_al_wahat_ad_dakhlah-egypt-56.jpg"),
                new SimpleImageItem().withIdentifier(22L).withName("Riedstadt Germany").withDescription("#100042").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/riedstadt-germany-42.jpg"),
                new SimpleImageItem().withIdentifier(23L).withName("Redwood City United States").withDescription("#100048").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/redwood_city-united_states-48.jpg"),
                new SimpleImageItem().withIdentifier(24L).withName("Nyingchi, Tibet China").withDescription("#100098").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/nyingchi,_tibet-china-98.jpg"),
                new SimpleImageItem().withIdentifier(25L).withName("Ngari, Tibet China").withDescription("#100057").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/ngari,_tibet-china-57.jpg"),
                new SimpleImageItem().withIdentifier(26L).withName("Pozoantiguo Spain").withDescription("#100099").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/pozoantiguo-spain-99.jpg"),
                new SimpleImageItem().withIdentifier(27L).withName("Ningaloo Australia").withDescription("#100073").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/ningaloo-australia-73.jpg"),
                new SimpleImageItem().withIdentifier(28L).withName("Niederzier Germany").withDescription("#100079").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/niederzier-germany-79.jpg"),
                new SimpleImageItem().withIdentifier(29L).withName("Olympic Dam Australia").withDescription("#100065").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/olympic_dam-australia-65.jpg"),
                new SimpleImageItem().withIdentifier(30L).withName("Peedamulla Australia").withDescription("#100040").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/peedamulla-australia-40.jpg"),
                new SimpleImageItem().withIdentifier(31L).withName("Nevado Tres Cruces Park Chile").withDescription("#100089").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/nevado_tres_cruces_park-chile-89.jpg")
        );
    }

    public static SimpleImageItem getDummyItem() {
        int ran = new Random().nextInt(3);
        if (ran == 0) {
            return new SimpleImageItem().withName("NEW").withDescription("Newly added item").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/yang_zhuo_yong_cuo,_tibet-china-63.jpg");
        } else if (ran == 1) {
            return new SimpleImageItem().withName("NEW").withDescription("Newly added item").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/yellowstone-united_states-17.jpg");
        } else {
            return new SimpleImageItem().withName("NEW").withDescription("Newly added item").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/victoria-australia-31.jpg");
        }
    }

    private static List<ImageItem> toList(ImageItem... imageItems) {
        return Arrays.asList(imageItems);
    }

    private static List<SimpleImageItem> toList(SimpleImageItem... imageItems) {
        return Arrays.asList(imageItems);
    }

}
