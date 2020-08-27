package pane;

import java.awt.Dimension;
import java.awt.IllegalComponentStateException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import javax.swing.JPanel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import paramer.Config;

public class FriendList extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FriendList() {
		setLayout(null);
		Refresh();
		Config.friendList = this;
	}
	
	/**更新在线好友*/
	public void OnlineRefresh() throws IllegalComponentStateException,NullPointerException {
		String[] uids = Config.onlineFriendData.split(",");
		Set<String> keys = Config.list.keySet();
		for(String str : keys) {
			//先设置全部离线
			Config.list.get(str).setOnline(false);
		}
		if(!Config.onlineFriendData.trim().equals("暂无在线好友!")&&!Config.onlineFriendData.trim().equals("")) {
			for(String uid : uids) {
				if(!uid.trim().equals("")) {
					RefreshData refreshData = Config.list.get(uid);
					refreshData.setOnline(true);//设置在线状态
				}
			}
		}
		
		Collection<RefreshData> refreshData = Config.list.values();
		ArrayList<RefreshData> list = new ArrayList<RefreshData>(refreshData);
		Collections.sort(list);
		
		this.removeAll();
		int i=0;
		for(RefreshData f : list) {
			f.setBounds(0, (i++)*50, 546, 60);
			this.add(f);
		}
		this.setPreferredSize(new Dimension(0,40*list.size()));
		this.updateUI();
	}
	
	/**更新好友列表显示*/
	public void Refresh() {
		
		JSONArray jsonArray = JSONArray.fromObject(Config.friendData);
		
		if(Config.list.size()==0) {//首次加载列表
			for(int i=0;i<jsonArray.size();i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				Config.list.put(jsonObject.getString("uid"), new RefreshData(jsonObject.getString("image"),jsonObject.getString("netName"),jsonObject.getString("info"),jsonObject.getString("uid")));
			}
		}else {//列表已存在
			for(int i=0;i<jsonArray.size();i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				String uid = jsonObject.getString("uid");
				RefreshData refreshData = Config.list.get(uid);
				if(refreshData!=null) {//已经存在
					refreshData.setNetName(jsonObject.getString("netName"));
					refreshData.setInfo(jsonObject.getString("info"));
					refreshData.setImage(jsonObject.getString("image"));
				}else {//不存在
					Config.list.put(jsonObject.getString("uid"), new RefreshData(jsonObject.getString("image"),jsonObject.getString("netName"),jsonObject.getString("info"),jsonObject.getString("uid")));
				}
			}
		}
		OnlineRefresh();
	}
}
