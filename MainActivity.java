package com.myproject.myhub.giveawayhub;

import android.app.AlertDialog.Builder;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.Session;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MainActivity
  extends ActionBarActivity
{
  Button backHome;
  private Session.StatusCallback callback = new Session.StatusCallback()
  {
    public void call(Session paramAnonymousSession, SessionState paramAnonymousSessionState, Exception paramAnonymousException)
    {
      MainActivity.this.onSessionStateChange(paramAnonymousSession, paramAnonymousSessionState, paramAnonymousException);
    }
  };
  private UiLifecycleHelper uiHelper;
  
  private void onSessionStateChange(Session paramSession, SessionState paramSessionState, Exception paramException)
  {
    if (paramSessionState.isOpened()) {
      getSupportFragmentManager().beginTransaction().replace(2131296338, new UserFragment()).commit();
    }
    while (!paramSessionState.isClosed()) {
      return;
    }
    getSupportFragmentManager().beginTransaction().replace(2131296338, new PlaceholderFragment()).commit();
    this.backHome.setVisibility(8);
  }
  
  public void backHome(View paramView)
  {
    getSupportFragmentManager().beginTransaction().replace(2131296338, new UserFragment()).commit();
    this.backHome.setVisibility(8);
  }
  
  public void donateMenu(View paramView)
  {
    getSupportFragmentManager().beginTransaction().replace(2131296338, new DonateFragment()).commit();
    this.backHome.setVisibility(0);
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    this.uiHelper.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968599);
    this.uiHelper = new UiLifecycleHelper(this, this.callback);
    this.uiHelper.onCreate(paramBundle);
    if (paramBundle == null) {
      getSupportFragmentManager().beginTransaction().add(2131296338, new PlaceholderFragment()).commit();
    }
    this.backHome = ((Button)findViewById(2131296337));
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    this.uiHelper.onDestroy();
  }
  
  public void onPause()
  {
    super.onPause();
    this.uiHelper.onPause();
  }
  
  public void onResume()
  {
    super.onResume();
    this.uiHelper.onResume();
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    this.uiHelper.onSaveInstanceState(paramBundle);
  }
  
  public void receiveMenu(View paramView)
  {
    getSupportFragmentManager().beginTransaction().replace(2131296338, new ReceiveFragment()).commit();
    this.backHome.setVisibility(0);
  }
  
  public static class DonateFragment
    extends Fragment
  {
    static final int REQUEST_TAKE_PHOTO = 100;
    Uri capturedImageURI = null;
    String categoryName;
    Button confirmDonation;
    String currentPhotoPath = null;
    LinearLayout donationView;
    ExpandableListAdapter expandableListAdapter;
    HashMap<String, List<String>> expandableListDetail;
    List<String> expandableListTitle;
    ExpandableListView expandableListView;
    TextView heading;
    RadioGroup itemStatus;
    EditText location;
    ImageView mImageView;
    LinearLayout mainView;
    EditText name;
    Button newItem;
    RadioButton radioButton;
    Button takePicture;
    
    private void captureImage()
    {
      Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
      File localFile2;
      File localFile1;
      if (localIntent.resolveActivity(getActivity().getPackageManager()) != null)
      {
        localFile2 = null;
        localFile1 = localFile2;
      }
      try
      {
        String str = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        localFile1 = localFile2;
        localFile2 = File.createTempFile("JPEG_" + str + "_", ".jpg", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
        localFile1 = localFile2;
        this.currentPhotoPath = localFile2.getAbsolutePath();
        localFile1 = localFile2;
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          Toast.makeText(getActivity().getApplicationContext(), "Unknown error", 1).show();
        }
      }
      if (localFile1 != null)
      {
        this.capturedImageURI = Uri.fromFile(localFile1);
        localIntent.putExtra("output", this.capturedImageURI);
        startActivityForResult(localIntent, 100);
      }
    }
    
    private boolean getLatLongFromAddress(String paramString)
    {
      new Thread(new Runnable()
      {
        /* Error */
        public void run()
        {
          // Byte code:
          //   0: new 37	org/apache/http/impl/client/DefaultHttpClient
          //   3: dup
          //   4: invokespecial 38	org/apache/http/impl/client/DefaultHttpClient:<init>	()V
          //   7: new 40	org/apache/http/client/methods/HttpGet
          //   10: dup
          //   11: aload_0
          //   12: getfield 28	com/myproject/fbapplication/MainActivity$DonateFragment$5:val$uri	Ljava/lang/String;
          //   15: invokespecial 43	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
          //   18: new 45	org/apache/http/impl/client/BasicResponseHandler
          //   21: dup
          //   22: invokespecial 46	org/apache/http/impl/client/BasicResponseHandler:<init>	()V
          //   25: invokeinterface 52 3 0
          //   30: checkcast 54	java/lang/String
          //   33: astore 5
          //   35: dconst_0
          //   36: invokestatic 60	java/lang/Double:valueOf	(D)Ljava/lang/Double;
          //   39: astore_3
          //   40: dconst_0
          //   41: invokestatic 60	java/lang/Double:valueOf	(D)Ljava/lang/Double;
          //   44: astore 4
          //   46: new 62	org/json/JSONObject
          //   49: dup
          //   50: aload 5
          //   52: invokespecial 63	org/json/JSONObject:<init>	(Ljava/lang/String;)V
          //   55: astore 5
          //   57: aload 5
          //   59: ldc 65
          //   61: invokevirtual 69	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
          //   64: checkcast 71	org/json/JSONArray
          //   67: invokevirtual 75	org/json/JSONArray:length	()I
          //   70: iconst_1
          //   71: if_icmpne +72 -> 143
          //   74: aload 5
          //   76: ldc 65
          //   78: invokevirtual 69	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
          //   81: checkcast 71	org/json/JSONArray
          //   84: iconst_0
          //   85: invokevirtual 79	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
          //   88: ldc 81
          //   90: invokevirtual 84	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
          //   93: ldc 86
          //   95: invokevirtual 84	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
          //   98: ldc 88
          //   100: invokevirtual 92	org/json/JSONObject:getDouble	(Ljava/lang/String;)D
          //   103: invokestatic 60	java/lang/Double:valueOf	(D)Ljava/lang/Double;
          //   106: astore_3
          //   107: aload 5
          //   109: ldc 65
          //   111: invokevirtual 69	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
          //   114: checkcast 71	org/json/JSONArray
          //   117: iconst_0
          //   118: invokevirtual 79	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
          //   121: ldc 81
          //   123: invokevirtual 84	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
          //   126: ldc 86
          //   128: invokevirtual 84	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
          //   131: ldc 94
          //   133: invokevirtual 92	org/json/JSONObject:getDouble	(Ljava/lang/String;)D
          //   136: dstore_1
          //   137: dload_1
          //   138: invokestatic 60	java/lang/Double:valueOf	(D)Ljava/lang/Double;
          //   141: astore 4
          //   143: aload_0
          //   144: getfield 26	com/myproject/fbapplication/MainActivity$DonateFragment$5:this$0	Lcom/myproject/fbapplication/MainActivity$DonateFragment;
          //   147: aload_0
          //   148: getfield 26	com/myproject/fbapplication/MainActivity$DonateFragment$5:this$0	Lcom/myproject/fbapplication/MainActivity$DonateFragment;
          //   151: invokevirtual 98	com/myproject/fbapplication/MainActivity$DonateFragment:getActivity	()Landroid/support/v4/app/FragmentActivity;
          //   154: aload_0
          //   155: getfield 26	com/myproject/fbapplication/MainActivity$DonateFragment$5:this$0	Lcom/myproject/fbapplication/MainActivity$DonateFragment;
          //   158: getfield 102	com/myproject/fbapplication/MainActivity$DonateFragment:itemStatus	Landroid/widget/RadioGroup;
          //   161: invokevirtual 107	android/widget/RadioGroup:getCheckedRadioButtonId	()I
          //   164: invokevirtual 113	android/support/v4/app/FragmentActivity:findViewById	(I)Landroid/view/View;
          //   167: checkcast 115	android/widget/RadioButton
          //   170: putfield 119	com/myproject/fbapplication/MainActivity$DonateFragment:radioButton	Landroid/widget/RadioButton;
          //   173: aload_0
          //   174: getfield 26	com/myproject/fbapplication/MainActivity$DonateFragment$5:this$0	Lcom/myproject/fbapplication/MainActivity$DonateFragment;
          //   177: getfield 122	com/myproject/fbapplication/MainActivity$DonateFragment:currentPhotoPath	Ljava/lang/String;
          //   180: invokestatic 128	android/graphics/BitmapFactory:decodeFile	(Ljava/lang/String;)Landroid/graphics/Bitmap;
          //   183: astore 5
          //   185: new 130	java/io/ByteArrayOutputStream
          //   188: dup
          //   189: invokespecial 131	java/io/ByteArrayOutputStream:<init>	()V
          //   192: astore 6
          //   194: aload 5
          //   196: getstatic 137	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
          //   199: bipush 100
          //   201: aload 6
          //   203: invokevirtual 143	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
          //   206: pop
          //   207: new 145	com/parse/ParseFile
          //   210: dup
          //   211: ldc -109
          //   213: aload 6
          //   215: invokevirtual 151	java/io/ByteArrayOutputStream:toByteArray	()[B
          //   218: invokespecial 154	com/parse/ParseFile:<init>	(Ljava/lang/String;[B)V
          //   221: astore 5
          //   223: aload 5
          //   225: invokevirtual 158	com/parse/ParseFile:saveInBackground	()Lbolts/Task;
          //   228: pop
          //   229: new 160	com/parse/ParseObject
          //   232: dup
          //   233: aload_0
          //   234: getfield 26	com/myproject/fbapplication/MainActivity$DonateFragment$5:this$0	Lcom/myproject/fbapplication/MainActivity$DonateFragment;
          //   237: invokevirtual 98	com/myproject/fbapplication/MainActivity$DonateFragment:getActivity	()Landroid/support/v4/app/FragmentActivity;
          //   240: invokevirtual 164	android/support/v4/app/FragmentActivity:getResources	()Landroid/content/res/Resources;
          //   243: ldc -91
          //   245: invokevirtual 171	android/content/res/Resources:getText	(I)Ljava/lang/CharSequence;
          //   248: invokeinterface 177 1 0
          //   253: invokespecial 178	com/parse/ParseObject:<init>	(Ljava/lang/String;)V
          //   256: astore 6
          //   258: aload 6
          //   260: ldc -76
          //   262: aload_0
          //   263: getfield 26	com/myproject/fbapplication/MainActivity$DonateFragment$5:this$0	Lcom/myproject/fbapplication/MainActivity$DonateFragment;
          //   266: getfield 182	com/myproject/fbapplication/MainActivity$DonateFragment:categoryName	Ljava/lang/String;
          //   269: invokevirtual 186	com/parse/ParseObject:put	(Ljava/lang/String;Ljava/lang/Object;)V
          //   272: aload 6
          //   274: ldc -68
          //   276: aload_0
          //   277: getfield 26	com/myproject/fbapplication/MainActivity$DonateFragment$5:this$0	Lcom/myproject/fbapplication/MainActivity$DonateFragment;
          //   280: getfield 192	com/myproject/fbapplication/MainActivity$DonateFragment:name	Landroid/widget/EditText;
          //   283: invokevirtual 197	android/widget/EditText:getText	()Landroid/text/Editable;
          //   286: invokevirtual 198	java/lang/Object:toString	()Ljava/lang/String;
          //   289: invokevirtual 186	com/parse/ParseObject:put	(Ljava/lang/String;Ljava/lang/Object;)V
          //   292: aload 6
          //   294: ldc -56
          //   296: aload 5
          //   298: invokevirtual 186	com/parse/ParseObject:put	(Ljava/lang/String;Ljava/lang/Object;)V
          //   301: aload 6
          //   303: ldc -54
          //   305: aload_0
          //   306: getfield 26	com/myproject/fbapplication/MainActivity$DonateFragment$5:this$0	Lcom/myproject/fbapplication/MainActivity$DonateFragment;
          //   309: getfield 204	com/myproject/fbapplication/MainActivity$DonateFragment:location	Landroid/widget/EditText;
          //   312: invokevirtual 197	android/widget/EditText:getText	()Landroid/text/Editable;
          //   315: invokevirtual 198	java/lang/Object:toString	()Ljava/lang/String;
          //   318: invokevirtual 186	com/parse/ParseObject:put	(Ljava/lang/String;Ljava/lang/Object;)V
          //   321: aload 6
          //   323: ldc -51
          //   325: aload_0
          //   326: getfield 26	com/myproject/fbapplication/MainActivity$DonateFragment$5:this$0	Lcom/myproject/fbapplication/MainActivity$DonateFragment;
          //   329: getfield 119	com/myproject/fbapplication/MainActivity$DonateFragment:radioButton	Landroid/widget/RadioButton;
          //   332: invokevirtual 208	android/widget/RadioButton:getText	()Ljava/lang/CharSequence;
          //   335: invokeinterface 177 1 0
          //   340: invokevirtual 186	com/parse/ParseObject:put	(Ljava/lang/String;Ljava/lang/Object;)V
          //   343: aload_3
          //   344: invokevirtual 212	java/lang/Double:doubleValue	()D
          //   347: dconst_0
          //   348: dcmpl
          //   349: ifeq +42 -> 391
          //   352: aload 4
          //   354: invokevirtual 212	java/lang/Double:doubleValue	()D
          //   357: dconst_0
          //   358: dcmpl
          //   359: ifeq +32 -> 391
          //   362: aload 6
          //   364: ldc -42
          //   366: aload_3
          //   367: invokevirtual 212	java/lang/Double:doubleValue	()D
          //   370: invokestatic 217	java/lang/Double:toString	(D)Ljava/lang/String;
          //   373: invokevirtual 186	com/parse/ParseObject:put	(Ljava/lang/String;Ljava/lang/Object;)V
          //   376: aload 6
          //   378: ldc -37
          //   380: aload 4
          //   382: invokevirtual 212	java/lang/Double:doubleValue	()D
          //   385: invokestatic 217	java/lang/Double:toString	(D)Ljava/lang/String;
          //   388: invokevirtual 186	com/parse/ParseObject:put	(Ljava/lang/String;Ljava/lang/Object;)V
          //   391: aload 6
          //   393: new 16	com/myproject/fbapplication/MainActivity$DonateFragment$5$1
          //   396: dup
          //   397: aload_0
          //   398: invokespecial 222	com/myproject/fbapplication/MainActivity$DonateFragment$5$1:<init>	(Lcom/myproject/fbapplication/MainActivity$DonateFragment$5;)V
          //   401: invokevirtual 225	com/parse/ParseObject:saveInBackground	(Lcom/parse/SaveCallback;)V
          //   404: return
          //   405: astore_3
          //   406: aload_0
          //   407: getfield 26	com/myproject/fbapplication/MainActivity$DonateFragment$5:this$0	Lcom/myproject/fbapplication/MainActivity$DonateFragment;
          //   410: invokevirtual 98	com/myproject/fbapplication/MainActivity$DonateFragment:getActivity	()Landroid/support/v4/app/FragmentActivity;
          //   413: invokevirtual 229	android/support/v4/app/FragmentActivity:getApplicationContext	()Landroid/content/Context;
          //   416: ldc -25
          //   418: iconst_1
          //   419: invokestatic 237	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
          //   422: invokevirtual 240	android/widget/Toast:show	()V
          //   425: return
          //   426: astore_3
          //   427: aload_0
          //   428: getfield 26	com/myproject/fbapplication/MainActivity$DonateFragment$5:this$0	Lcom/myproject/fbapplication/MainActivity$DonateFragment;
          //   431: invokevirtual 98	com/myproject/fbapplication/MainActivity$DonateFragment:getActivity	()Landroid/support/v4/app/FragmentActivity;
          //   434: invokevirtual 229	android/support/v4/app/FragmentActivity:getApplicationContext	()Landroid/content/Context;
          //   437: ldc -14
          //   439: iconst_1
          //   440: invokestatic 237	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
          //   443: invokevirtual 240	android/widget/Toast:show	()V
          //   446: return
          // Local variable table:
          //   start	length	slot	name	signature
          //   0	447	0	this	5
          //   136	2	1	d	double
          //   39	328	3	localDouble1	Double
          //   405	1	3	localException1	Exception
          //   426	1	3	localException2	Exception
          //   44	337	4	localDouble2	Double
          //   33	264	5	localObject1	Object
          //   192	200	6	localObject2	Object
          // Exception table:
          //   from	to	target	type
          //   0	35	405	java/lang/Exception
          //   46	57	426	java/lang/Exception
          //   57	137	426	java/lang/Exception
        }
      }).start();
      return true;
    }
    
    private void setFullImageFromFilePath(String paramString, ImageView paramImageView)
    {
      Options localOptions = new Options();
      localOptions.inJustDecodeBounds = true;
      BitmapFactory.decodeFile(paramString, localOptions);
      localOptions.inJustDecodeBounds = false;
      paramImageView.setImageBitmap(BitmapFactory.decodeFile(paramString, localOptions));
    }
    
    public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
    {
      if ((paramInt1 == 100) && (paramInt2 == -1))
      {
        setFullImageFromFilePath(this.currentPhotoPath, this.mImageView);
        return;
      }
      Toast.makeText(getActivity().getApplicationContext(), "Image Capture Failed", 1).show();
      this.currentPhotoPath = null;
    }
    
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
      paramLayoutInflater = paramLayoutInflater.inflate(2130968619, paramViewGroup, false);
      this.mainView = ((LinearLayout)paramLayoutInflater.findViewById(2131296372));
      this.donationView = ((LinearLayout)paramLayoutInflater.findViewById(2131296378));
      this.expandableListView = ((ExpandableListView)paramLayoutInflater.findViewById(2131296375));
      this.expandableListDetail = ExpandableListData.getData();
      this.expandableListTitle = new ArrayList(this.expandableListDetail.keySet());
      this.expandableListAdapter = new ExpandableListAdapter(getActivity().getApplicationContext(), this.expandableListTitle, this.expandableListDetail);
      this.expandableListView.setAdapter(this.expandableListAdapter);
      this.heading = ((TextView)paramLayoutInflater.findViewById(2131296373));
      this.heading.setText("Donate");
      this.newItem = ((Button)paramLayoutInflater.findViewById(2131296376));
      this.newItem.setVisibility(0);
      this.newItem.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          DonateFragment.this.mainView.setVisibility(8);
          DonateFragment.this.donationView.setVisibility(0);
          DonateFragment.this.categoryName = "Others";
        }
      });
      this.name = ((EditText)paramLayoutInflater.findViewById(2131296365));
      this.location = ((EditText)paramLayoutInflater.findViewById(2131296366));
      this.itemStatus = ((RadioGroup)paramLayoutInflater.findViewById(2131296380));
      this.expandableListView.setOnChildClickListener(new OnChildClickListener()
      {
        public boolean onChildClick(ExpandableListView paramAnonymousExpandableListView, View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2, long paramAnonymousLong)
        {
          DonateFragment.this.mainView.setVisibility(8);
          DonateFragment.this.donationView.setVisibility(0);
          DonateFragment.this.name.setText((CharSequence)((List)DonateFragment.this.expandableListDetail.get(DonateFragment.this.expandableListTitle.get(paramAnonymousInt1))).get(paramAnonymousInt2));
          DonateFragment.this.name.setKeyListener(null);
          DonateFragment.this.categoryName = ((String)DonateFragment.this.expandableListTitle.get(paramAnonymousInt1));
          return true;
        }
      });
      this.mImageView = ((ImageView)paramLayoutInflater.findViewById(2131296368));
      this.takePicture = ((Button)paramLayoutInflater.findViewById(2131296367));
      this.takePicture.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          DonateFragment.this.captureImage();
        }
      });
      this.confirmDonation = ((Button)paramLayoutInflater.findViewById(2131296369));
      this.confirmDonation.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (DonateFragment.this.name.length() == 0)
          {
            Toast.makeText(DonateFragment.this.getActivity().getApplicationContext(), "Please provide item name", 1).show();
            DonateFragment.this.name.requestFocus();
            return;
          }
          if (DonateFragment.this.location.length() == 0)
          {
            Toast.makeText(DonateFragment.this.getActivity().getApplicationContext(), "Please provide location of the item", 1).show();
            DonateFragment.this.location.requestFocus();
            return;
          }
          if (DonateFragment.this.itemStatus.getCheckedRadioButtonId() == -1)
          {
            Toast.makeText(DonateFragment.this.getActivity().getApplicationContext(), "Please provide status of the item", 1).show();
            return;
          }
          if (DonateFragment.this.currentPhotoPath == null)
          {
            DonateFragment.this.captureImage();
            return;
          }
          DonateFragment.this.getLatLongFromAddress(DonateFragment.this.location.getText().toString());
        }
      });
      if (!getActivity().getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.camera"))
      {
        Toast.makeText(getActivity().getApplicationContext(), "Sorry! Your device doesn't support camera", 1).show();
        getActivity().finish();
      }
      return paramLayoutInflater;
    }
  }
  
  public static class PlaceholderFragment
    extends Fragment
  {
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
      return paramLayoutInflater.inflate(2130968616, paramViewGroup, false);
    }
  }
  
  public static class ReceiveFragment
    extends Fragment
  {
    ListViewAdapter adapter;
    int childPosition = -1;
    Button clearResults;
    Button distance;
    EditText distanceInput;
    int distanceValue = 0;
    ExpandableListAdapter expandableListAdapter;
    HashMap<String, List<String>> expandableListData;
    List<String> expandableListTitle;
    ExpandableListView expandableListView;
    GPSTracker gps;
    int groupPosition = -1;
    TextView heading;
    List<ParseData> list;
    Button newProducts;
    Button otherItems;
    Button recent;
    TextView resultsCount;
    TextView resultsHeading;
    ListView resultsListView;
    RelativeLayout resultsView;
    SearchManager searchManager;
    SearchView searchView;
    
    private boolean checkDistance(Double paramDouble1, Double paramDouble2)
    {
      Location localLocation1 = new Location("");
      localLocation1.setLatitude(this.gps.getLatitude());
      localLocation1.setLongitude(this.gps.getLongitude());
      Location localLocation2 = new Location("");
      localLocation2.setLatitude(paramDouble1.doubleValue());
      localLocation2.setLongitude(paramDouble2.doubleValue());
      return this.distanceValue * 1000.0D < localLocation1.distanceTo(localLocation2);
    }
    
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
      paramLayoutInflater = paramLayoutInflater.inflate(2130968619, paramViewGroup, false);
      this.resultsHeading = ((TextView)paramLayoutInflater.findViewById(2131296384));
      this.resultsCount = ((TextView)paramLayoutInflater.findViewById(2131296385));
      this.resultsListView = ((ListView)paramLayoutInflater.findViewById(2131296391));
      this.resultsView = ((RelativeLayout)paramLayoutInflater.findViewById(2131296383));
      this.expandableListView = ((ExpandableListView)paramLayoutInflater.findViewById(2131296375));
      this.expandableListData = ExpandableListData.getData();
      this.expandableListTitle = new ArrayList(this.expandableListData.keySet());
      this.expandableListAdapter = new ExpandableListAdapter(getActivity().getApplicationContext(), this.expandableListTitle, this.expandableListData);
      this.expandableListView.setAdapter(this.expandableListAdapter);
      this.expandableListView.setOnChildClickListener(new OnChildClickListener()
      {
        public boolean onChildClick(ExpandableListView paramAnonymousExpandableListView, View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2, long paramAnonymousLong)
        {
          ReceiveFragment.this.searchItems(paramAnonymousInt1, paramAnonymousInt2, 0);
          ReceiveFragment.this.groupPosition = paramAnonymousInt1;
          ReceiveFragment.this.childPosition = paramAnonymousInt2;
          return false;
        }
      });
      this.searchManager = ((SearchManager)getActivity().getSystemService("search"));
      this.searchView = ((SearchView)paramLayoutInflater.findViewById(2131296374));
      this.searchView.setVisibility(0);
      this.searchView.setSearchableInfo(this.searchManager.getSearchableInfo(getActivity().getComponentName()));
      this.searchView.setIconified(false);
      this.searchView.setOnQueryTextListener(new OnQueryTextListener()
      {
        HashMap<String, List<String>> newExpandableListAdapter;
        
        public boolean onQueryTextChange(String paramAnonymousString)
        {
          ReceiveFragment.this.updateAdapter(this.newExpandableListAdapter, paramAnonymousString);
          return false;
        }
        
        public boolean onQueryTextSubmit(String paramAnonymousString)
        {
          ReceiveFragment.this.updateAdapter(this.newExpandableListAdapter, paramAnonymousString);
          return false;
        }
      });
      this.heading = ((TextView)paramLayoutInflater.findViewById(2131296373));
      this.heading.setText("Receive");
      this.otherItems = ((Button)paramLayoutInflater.findViewById(2131296377));
      this.otherItems.setVisibility(0);
      this.otherItems.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ReceiveFragment.this.searchItems(-1, -1, 0);
        }
      });
      this.clearResults = ((Button)paramLayoutInflater.findViewById(2131296387));
      this.clearResults.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ReceiveFragment.this.resultsListView.setVisibility(8);
          ReceiveFragment.this.searchView.setQuery(null, false);
          ReceiveFragment.this.searchView.setVisibility(0);
          ReceiveFragment.this.expandableListView.setVisibility(0);
          ReceiveFragment.this.otherItems.setVisibility(0);
          ReceiveFragment.this.getFragmentManager().beginTransaction().replace(2131296338, new ReceiveFragment()).commit();
        }
      });
      this.recent = ((Button)paramLayoutInflater.findViewById(2131296388));
      this.recent.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ReceiveFragment.this.searchItems(ReceiveFragment.this.groupPosition, ReceiveFragment.this.childPosition, 1);
        }
      });
      this.distance = ((Button)paramLayoutInflater.findViewById(2131296389));
      this.distance.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ReceiveFragment.this.distanceInput = new EditText(ReceiveFragment.this.getActivity().getApplicationContext());
          ReceiveFragment.this.distanceInput.setTextColor(-16777216);
          ReceiveFragment.this.distanceInput.setInputType(2);
          new AlertDialog.Builder(ReceiveFragment.this.getActivity()).setTitle("Distance filter").setMessage("Provide distance to search for items").setView(ReceiveFragment.this.distanceInput).setPositiveButton("Search", new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              ReceiveFragment.this.distanceValue = Integer.parseInt(ReceiveFragment.this.distanceInput.getText().toString());
              ReceiveFragment.this.searchItems(ReceiveFragment.this.groupPosition, ReceiveFragment.this.childPosition, 2);
            }
          }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
          }).show();
        }
      });
      this.newProducts = ((Button)paramLayoutInflater.findViewById(2131296390));
      this.newProducts.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ReceiveFragment.this.searchItems(ReceiveFragment.this.groupPosition, ReceiveFragment.this.childPosition, 3);
        }
      });
      return paramLayoutInflater;
    }
    
    public boolean searchItems(int paramInt1, int paramInt2, int paramInt3)
    {
      if ((paramInt3 == 2) && (this.distanceValue < 1))
      {
        Toast.makeText(getActivity().getApplicationContext(), "Distance range is invalid", 1).show();
        return false;
      }
      this.list = new ArrayList();
      for (;;)
      {
        try
        {
          localObject = new ParseQuery(getActivity().getResources().getText(2131427418).toString());
          if (paramInt1 == -1) {
            break label272;
          }
          ((ParseQuery)localObject).whereEqualTo("itemName", this.expandableListView.getExpandableListAdapter().getChild(paramInt1, paramInt2));
        }
        catch (Exception localException)
        {
          Object localObject;
          ParseObject localParseObject;
          ParseData localParseData;
          Toast.makeText(getActivity().getApplicationContext(), "Unknown error!", 1).show();
          return false;
        }
        localObject = ((ParseQuery)localObject).find().iterator();
        if (((Iterator)localObject).hasNext())
        {
          localParseObject = (ParseObject)((Iterator)localObject).next();
          if ((paramInt3 == 2) && (!checkDistance(Double.valueOf(Double.parseDouble(localParseObject.getString("latitude"))), Double.valueOf(Double.parseDouble(localParseObject.getString("longitude")))))) {
            continue;
          }
          localParseData = new ParseData();
          localParseData.setReceiveItemName(localParseObject.getString("itemName"));
          localParseData.setReceiveItemLocation(localParseObject.getString("itemLocation"));
          localParseData.setImageBytes(localParseObject.getParseFile("image").getData());
          localParseData.setId(localParseObject.getObjectId());
          this.list.add(localParseData);
          continue;
          label272:
          localException.whereEqualTo("categoryName", "Others");
          break label529;
          localException.addDescendingOrder("createdAt");
          continue;
          this.gps = new GPSTracker(getActivity().getApplicationContext());
          localException.whereExists("latitude");
          localException.whereExists("longitude");
          continue;
          localException.whereEqualTo("itemStatus", "New");
        }
        else
        {
          this.adapter = new ListViewAdapter(getActivity(), this.list, paramInt1);
          this.resultsListView.setAdapter(this.adapter);
          this.adapter.notifyDataSetChanged();
          if (paramInt1 == -1) {
            this.resultsHeading.setText("Searching for other Products");
          }
          for (;;)
          {
            this.resultsCount.setText("Results found: " + this.list.size());
            this.searchView.setVisibility(8);
            this.expandableListView.setVisibility(8);
            this.otherItems.setVisibility(8);
            this.resultsView.setVisibility(0);
            this.resultsListView.setVisibility(0);
            return false;
            this.resultsHeading.setText("Searching for " + this.expandableListView.getExpandableListAdapter().getChild(paramInt1, paramInt2));
          }
        }
        label529:
        switch (paramInt3)
        {
        }
      }
    }
    
    public void updateAdapter(HashMap<String, List<String>> paramHashMap, String paramString)
    {
      paramHashMap = new HashMap(this.expandableListAdapter.filterData(paramString));
      this.expandableListView.setAdapter(new ExpandableListAdapter(getActivity().getApplicationContext(), new ArrayList(paramHashMap.keySet()), paramHashMap));
      if (!paramString.isEmpty())
      {
        int j = paramHashMap.size();
        int i = 0;
        while (i < j)
        {
          this.expandableListView.expandGroup(i);
          i += 1;
        }
      }
    }
  }
  
  public static class UserFragment
    extends Fragment
  {
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
      return paramLayoutInflater.inflate(2130968622, paramViewGroup, false);
    }
  }
}