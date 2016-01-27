package com.awok.moshin.intigraltestapp.Utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.widget.ImageView;

/**
 * Created by mohsin on 9/9/2015.
 */
public class Constants {
    public static String API_SERVER_URL = "http://api.themoviedb.org/3/movie/";
    public static String PREFS_NAME  = "Intigral-Prefs";
    public static Integer Success = 0;
    public static Integer Exception = -1;
    public static String API_KEY = "9944765f578d7b5224ff43a56dde5562";
    public static String IMAGE_BASE_PATH = "http://image.tmdb.org/t/p/w150/";
    public static String LARGE_IMAGE_BASE_PATH = "http://image.tmdb.org/t/p/w500/";
    public static String connectionTimeOutMessage = "Internet Connection is Slow!";
    public static String JSON_PRODUCT_LIST_NAME = "PRODUCTS";
    public static String JSON_CATEGORY_LIST_NAME = "categories";
    public static String CAT_ARRAY_INTENT = "categoryArrayListIntent";
    public static String SEARCH_FILTER_INTENT = "searchFilterIntent";
    public static String CAT_PARENT_ID_INTENT = "catParentIdIntent";
    public static String CAT_ID_INTENT = "catIdIntent";
    public static String CAT_DEPTH_LEVEL_INTENT = "catDepthLevelIntent";
    public static String CAT_NAME_INTENT = "catNameIntent";
    public static String SHIPPING_RESPONSE_INTENT = "shippingResponseIntent";
    public static String USER_MOBILE_PREFS = "userMobileNumberInPrefs";
    public static String USER_SETTING_COUNTRY="userCountryPrefs";
    public static String USER_COUNTRY_IMAGE_ID="userCountryImageIdPrefs";
    public static String USER_ID_PREFS = "userIdInPrefs";
    public static String SHIPPING_METHOD_PREFS = "shippingMethodInPrefs";
    public static String CELL_WIDTH_PREFS = "shippingMethodInPrefs";
    public static String USER_AUTH_TOKEN_PREFS = "userAuthorisationTokenInPrefs";
    public static String CAT_LIST_PREFS = "categoriesListInPrefs";
    public static String PRODUCT_ID_INTENT = "productId";
    public static String QUANTITY_INTENT = "quantity";
    public static String STOCK_INTENT = "stockQuantity";
    public static String VARIANTID_INTENT = "variantId";
    public static String SELECTED_METHOD_INTENT = "selectedMethod";
    public static String PRODUCT_NAME_INTENT = "productName";
    public static String PRODUCT_DISCOUNT_PERCENTAGE_INTENT = "productDiscountPercentage";
    public static String PRODUCT_IMAGE_INTENT = "productImage";
    public static String PRODUCT_PRICE_NEW_INTENT = "productPrieNew";
    public static String PRODUCT_VARIANTS_INTENT = "productVariants";
    public static String PRODUCT_SPECS_INTENT = "productSpecs";
    public static String PRODUCT_PRICE_OLD_INTENT = "productPriceOld";
    public static String PRODUCT_DESCRIPTION_INTENT = "productDescription";
    public static String PRODUCT_RATING = "productRating";
    public static String PRODUCT_RATING_COUNT = "productRatingCount";
    public static String APP_CART_COUNT = "productRatingCount";



    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
