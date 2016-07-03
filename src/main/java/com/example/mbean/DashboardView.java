package com.example.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.faces.event.ValueChangeEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

public class DashboardView {

	private boolean switchView;
	private String homeDashBoardTabindex = "0";
	private DashboardModel model;
	private boolean includeOfflineUsers;
	private boolean showMICharts;
	private int noOfColumnsForDashBoard = 2;
	private int tabIndex = 1;

	public boolean isIncludeOfflineUsers() {
		return includeOfflineUsers;
	}

	public void setIncludeOfflineUsers(boolean includeOfflineUsers) {
		this.includeOfflineUsers = includeOfflineUsers;
	}

	public boolean isShowMICharts() {
		return showMICharts;
	}

	public void setShowMICharts(boolean showMICharts) {
		this.showMICharts = showMICharts;
	}

	public int getNoOfColumnsForDashBoard() {
		return noOfColumnsForDashBoard;
	}

	public void setNoOfColumnsForDashBoard(int noOfColumnsForDashBoard) {
		this.noOfColumnsForDashBoard = noOfColumnsForDashBoard;
	}

	public String getHomeDashBoardTabindex() {
		return homeDashBoardTabindex;
	}

	public void setHomeDashBoardTabindex(String homeDashBoardTabindex) {
		this.homeDashBoardTabindex = homeDashBoardTabindex;
	}

	public boolean isSwitchView() {
		return switchView;
	}

	public void setSwitchView(boolean switchView) {
		this.switchView = switchView;
	}

	public void handleReorder(DashboardReorderEvent event) {
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("dashboard:dashBoardDetail");
	}

	public void loadDashboard() {

		setIncludeOfflineUsers(false);
		loadTabTogglerList();
	}

	public DashboardView() {
		// Initialize the dashboard model
		this.model = new DefaultDashboardModel();
		// Initialize the dashboard column #1
		DashboardColumn column1 = new DefaultDashboardColumn();
		// Initialize the dashboard column #2
		DashboardColumn column2 = new DefaultDashboardColumn();
		// Add widgets into column1
		column1.addWidget("complaintsByCompanyUnit");
		column2.addWidget("complaintsByProduct");
		this.model.addColumn(column1);
		this.model.addColumn(column2);
	}

	public int getTabIndex() {
		return tabIndex;
	}

	public DashboardModel getModel() {
		return model;
	}

	public void setModel(DashboardModel model) {
		this.model = model;
	}

	/**************************************************** Functions For Tab Toggler ************************************************************/

	private Map<String, CustomTab> customTabsMap = new HashMap<String, CustomTab>();

	public Map<String, CustomTab> getHmTabList() {
		return customTabsMap;
	}

	public void setHmTabList(Map<String, CustomTab> hmTabList) {
		this.customTabsMap = hmTabList;
	}

	private boolean chkBoxValue = false;

	public boolean isChkBoxValue() {
		return chkBoxValue;
	}

	public void setChkBoxValue(boolean chkBoxValue) {
		this.chkBoxValue = chkBoxValue;
	}

	private String tempTabindex;

	public String getTempTabindex() {
		return tempTabindex;
	}

	public void setTempTabindex(String tempTabindex) {
		this.tempTabindex = tempTabindex;
	}

	int listSize = 0, count = 0;
	int intTabindex = 0, intActiveIndex = 0;
	Boolean identity = false;
	private int uniqueIndex = 0;
	String strIndex = null;

	/**
	 * function to select tab on click of tab
	 */
	public void onSelectdashBoardDetTabMenu(String index) {
		try {
			setHomeDashBoardTabindex(index);

			if (null != index) {
				loadTabs(index);
			}

			genratetempActiveIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * function to add tabs on load
	 */

	public void loadTabTogglerList() {
		try {

			if (customTabsMap.size() == 0) {

				uniqueIndex = -1;

				strIndex = getIndex();
				customTabsMap.put(strIndex, new CustomTab("My View", true,
						strIndex));
				strIndex = getIndex();
				customTabsMap.put(strIndex, new CustomTab("Application Users",
						true, strIndex));
				strIndex = getIndex();
				customTabsMap.put(strIndex,
						new CustomTab("Tab", true, strIndex));

				setHomeDashBoardTabindex("0");
				setTempTabindex("0");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * function to get unique index
	 */

	public String getIndex() {
		try {
			uniqueIndex = uniqueIndex + 1;
			strIndex = "" + uniqueIndex;
		} catch (Exception e) {
		}
		return strIndex;
	}

	/**
	 * function for tab name change
	 */

	public void changeTabNameListener(CustomTab tabMenuObj) {

		try {
			if (tabMenuObj != null) {
				customTabsMap.put(tabMenuObj.tabIndex, tabMenuObj);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * function to add new tab
	 */

	public void addNewTabListener() {
		try {
			strIndex = getIndex();
			customTabsMap.put(strIndex,
					new CustomTab("New Tab", true, strIndex));
			chkBoxValue = true;
			tabMenuCheckBoxListener(strIndex);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * function to remove tab
	 */
	public void removeTabListener() {
		try {

			strIndex = homeDashBoardTabindex;

			if (customTabsMap.size() > 0) {

				chkBoxValue = false;
				tabMenuCheckBoxListener(homeDashBoardTabindex);

				if (customTabsMap.containsKey(strIndex))
					customTabsMap.remove(strIndex);
			} else {
				customTabsMap.clear();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Listener function for checkbox value change
	 */

	public void tabMenuValueChangeListener(ValueChangeEvent event) {
		try {
			// chkBoxValue = (boolean) event.getNewValue();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * function to generate tab index
	 */

	public void genratetempActiveIndex() {
		try {
			count = -1;

			for (Map.Entry<String, CustomTab> entry : customTabsMap.entrySet()) {
				if (entry.getValue().getTabShow()) {
					count++;
					if (entry.getValue().getTabIndex()
							.equalsIgnoreCase(homeDashBoardTabindex))
						break;
				}

			}

			setTempTabindex("" + count);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Listener function for checkbox Based on true / false value of check box
	 * respective tab is shown
	 */

	public void tabMenuCheckBoxListener(String menuTabindex) {
		try {

			intTabindex = Integer.parseInt(menuTabindex);
			intActiveIndex = Integer.parseInt(homeDashBoardTabindex);

			identity = false;

			if (customTabsMap.containsKey(menuTabindex))
				customTabsMap.get(menuTabindex).setTabShow(chkBoxValue);

			if (!chkBoxValue) {
				if (homeDashBoardTabindex.equalsIgnoreCase(menuTabindex)) {

					ArrayList<String> keys = new ArrayList<String>(
							customTabsMap.keySet());

					intActiveIndex = keys.indexOf(menuTabindex);

					for (int i = intActiveIndex; i < keys.size(); i++) {
						if (customTabsMap.containsKey(keys.get(i)))
							if (customTabsMap.get(keys.get(i)).getTabShow()) {
								setHomeDashBoardTabindex(customTabsMap.get(
										keys.get(i)).getTabIndex());
								identity = true;
								break;
							}
					}

					if (!identity)
						for (int j = intActiveIndex; j >= 0; j--) {
							if (customTabsMap.containsKey(keys.get(j)))
								if (customTabsMap.get(keys.get(j)).getTabShow()) {
									setHomeDashBoardTabindex(customTabsMap.get(
											keys.get(j)).getTabIndex());
									identity = true;
									break;
								}
						}

					if (identity) {
						loadTabs(homeDashBoardTabindex);
					}
				} else
					loadTabs(homeDashBoardTabindex);

			} else if (chkBoxValue) {

				setHomeDashBoardTabindex(menuTabindex);
				loadTabs(homeDashBoardTabindex);

			}

			genratetempActiveIndex();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * function to load tabs based on index
	 */

	public void loadTabs(String homeDashBoardTabindex) {
		try {/*
			 * switch (homeDashBoardTabindex) { case "0": {
			 * chartMbean.initializeCharts();
			 * RequestContext.getCurrentInstance().execute("loadDashBoard();");
			 * }
			 * 
			 * break;
			 * 
			 * case "1": usersList = new ApplicationUsersDataModel(loginService,
			 * isIncludeOfflineUsers()); setSwitchView(false);
			 * 
			 * break;
			 * 
			 * default: break; }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Class for dynamic tab name, index and show values
	 */
	public class CustomTab implements Serializable {

		private static final long serialVersionUID = 1L;

		private String tabName;
		private Boolean tabShow;
		private String tabIndex;

		public CustomTab(String tabName, Boolean tabShow, String tabIndex) {
			this.tabName = tabName;
			this.tabShow = tabShow;
			this.tabIndex = tabIndex;
		}

		public String getTabIndex() {
			return tabIndex;
		}

		public void setTabIndex(String tabIndex) {
			this.tabIndex = tabIndex;
		}

		public String getTabName() {
			return tabName;
		}

		public void setTabName(String tabName) {
			this.tabName = tabName;
		}

		public Boolean getTabShow() {
			return tabShow;
		}

		public void setTabShow(Boolean tabShow) {
			this.tabShow = tabShow;
		}

	}

}
