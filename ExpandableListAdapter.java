package com.myproject.myhub.giveawayhub;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpandableListAdapter
  extends BaseExpandableListAdapter
{
  private Context context;
  private HashMap<String, List<String>> expandableListData;
  private List<String> expandableListTitle;
  
  public ExpandableListAdapter(Context paramContext, List<String> paramList, HashMap<String, List<String>> paramHashMap)
  {
    this.context = paramContext;
    this.expandableListTitle = paramList;
    this.expandableListData = paramHashMap;
  }
  
  public HashMap<String, List<String>> filterData(String paramString)
  {
    Object localObject = new HashMap(ExpandableListData.getData());
    ArrayList localArrayList1 = new ArrayList(((HashMap)localObject).keySet());
    ArrayList localArrayList2 = new ArrayList(((HashMap)localObject).values());
    HashMap localHashMap = new HashMap();
    if (paramString.isEmpty()) {
      localHashMap.putAll((Map)localObject);
    }
    for (;;)
    {
      return localHashMap;
      int i = 0;
      while (i < localArrayList2.size())
      {
        localObject = new ArrayList();
        int j = 0;
        while (j < ((List)localArrayList2.get(i)).size())
        {
          if (((String)((List)localArrayList2.get(i)).get(j)).toLowerCase().contains(paramString.toLowerCase()))
          {
            Log.d((String)localArrayList1.get(i), (String)((List)localArrayList2.get(i)).get(j));
            ((List)localObject).add(((List)localArrayList2.get(i)).get(j));
          }
          j += 1;
        }
        if (!((List)localObject).isEmpty()) {
          localHashMap.put(localArrayList1.get(i), localObject);
        }
        i += 1;
      }
    }
  }
  
  public Object getChild(int paramInt1, int paramInt2)
  {
    return ((List)this.expandableListData.get(this.expandableListTitle.get(paramInt1))).get(paramInt2);
  }
  
  public long getChildId(int paramInt1, int paramInt2)
  {
    return paramInt2;
  }
  
  public View getChildView(int paramInt1, int paramInt2, boolean paramBoolean, View paramView, ViewGroup paramViewGroup)
  {
    String str = (String)getChild(paramInt1, paramInt2);
    paramViewGroup = paramView;
    if (paramView == null) {
      paramViewGroup = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130968618, null);
    }
    paramView = (TextView)paramViewGroup.findViewById(2131296371);
    paramView.setTextColor(-16777216);
    paramView.setText(str);
    return paramViewGroup;
  }
  
  public int getChildrenCount(int paramInt)
  {
    return ((List)this.expandableListData.get(this.expandableListTitle.get(paramInt))).size();
  }
  
  public Object getGroup(int paramInt)
  {
    return this.expandableListTitle.get(paramInt);
  }
  
  public int getGroupCount()
  {
    return this.expandableListTitle.size();
  }
  
  public long getGroupId(int paramInt)
  {
    return paramInt;
  }
  
  public View getGroupView(int paramInt, boolean paramBoolean, View paramView, ViewGroup paramViewGroup)
  {
    String str = (String)getGroup(paramInt);
    paramViewGroup = paramView;
    if (paramView == null) {
      paramViewGroup = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130968617, null);
    }
    paramView = (TextView)paramViewGroup.findViewById(2131296370);
    paramView.setTextColor(-65536);
    paramView.setTypeface(null, 1);
    paramView.setText(str);
    return paramViewGroup;
  }
  
  public boolean hasStableIds()
  {
    return false;
  }
  
  public boolean isChildSelectable(int paramInt1, int paramInt2)
  {
    return true;
  }
}