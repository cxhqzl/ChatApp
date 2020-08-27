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
	
	/**�������ߺ���*/
	public void OnlineRefresh() throws IllegalComponentStateException,NullPointerException {
		String[] uids = Config.onlineFriendData.split(",");
		Set<String> keys = Config.list.keySet();
		for(String str : keys) {
			//������ȫ������
			Config.list.get(str).setOnline(false);
		}
		if(!Config.onlineFriendData.trim().equals("�������ߺ���!")&&!Config.onlineFriendData.trim().equals("")) {
			for(String uid : uids) {
				if(!uid.trim().equals("")) {
					RefreshData refreshData = Config.list.get(uid);
					refreshData.setOnline(true);//��������״̬
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
	
	/**���º����б���ʾ*/
	public void Refresh() {
		
		JSONArray jsonArray = JSONArray.fromObject(Config.friendData);
		
		if(Config.list.size()==0) {//�״μ����б�
			for(int i=0;i<jsonArray.size();i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				Config.list.put(jsonObject.getString("uid"), new RefreshData(jsonObject.getString("image"),jsonObject.getString("netName"),jsonObject.getString("info"),jsonObject.getString("uid")));
			}
		}else {//�б��Ѵ���
			for(int i=0;i<jsonArray.size();i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				String uid = jsonObject.getString("uid");
				RefreshData refreshData = Config.list.get(uid);
				if(refreshData!=null) {//�Ѿ�����
					refreshData.setNetName(jsonObject.getString("netName"));
					refreshData.setInfo(jsonObject.getString("info"));
					refreshData.setImage(jsonObject.getString("image"));
				}else {//������
					Config.list.put(jsonObject.getString("uid"), new RefreshData(jsonObject.getString("image"),jsonObject.getString("netName"),jsonObject.getString("info"),jsonObject.getString("uid")));
				}
			}
		}
		OnlineRefresh();
	}
}
