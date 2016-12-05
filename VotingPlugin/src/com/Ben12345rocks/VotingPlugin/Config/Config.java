package com.Ben12345rocks.VotingPlugin.Config;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;

import com.Ben12345rocks.AdvancedCore.YML.YMLFile;
import com.Ben12345rocks.VotingPlugin.Main;

// TODO: Auto-generated Javadoc
/**
 * The Class Config.
 */
public class Config extends YMLFile {

	/** The instance. */
	static Config instance = new Config();

	/** The plugin. */
	static Main plugin = Main.plugin;

	/**
	 * Gets the single instance of Config.
	 *
	 * @return single instance of Config
	 */
	public static Config getInstance() {
		return instance;
	}

	/**
	 * Instantiates a new config.
	 */
	public Config() {
		super(new File(Main.plugin.getDataFolder(), "Config.yml"));
	}

	/**
	 * Gets the debug enabled.
	 *
	 * @return the debug enabled
	 */
	public boolean getDebugEnabled() {
		return getData().getBoolean("Debug");
	}

	/**
	 * Gets the log debug to file.
	 *
	 * @return the log debug to file
	 */
	public boolean getLogDebugToFile() {
		return getData().getBoolean("LogDebugToFile", true);
	}

	/**
	 * Gets the request API default method.
	 *
	 * @return the request API default method
	 */
	public String getRequestAPIDefaultMethod() {
		return getData().getString("RequestAPI.DefaultMethod", "Anvil");
	}

	/**
	 * Gets the request API disabled methods.
	 *
	 * @return the request API disabled methods
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getRequestAPIDisabledMethods() {
		return (ArrayList<String>) getData().getList("RequestAPI.DisabledMethods", new ArrayList<String>());
	}

	/**
	 * Gets the time zone.
	 *
	 * @return the time zone
	 */
	public String getTimeZone() {
		return getData().getString("TimeZone", "UTC");

	}

	/**
	 * Gets the debug info ingame.
	 *
	 * @return the debug info ingame
	 */
	public boolean getDebugInfoIngame() {
		return getData().getBoolean("DebugInfoIngame");
	}

	/**
	 * Gets the commands vote auto input sites.
	 *
	 * @return the commands vote auto input sites
	 */
	public boolean getFormatCommandsVoteAutoInputSites() {
		return getData().getBoolean("Format.Commands.Vote.AutoInputSites");
	}

	/**
	 * Gets the commands vote help line.
	 *
	 * @return the commands vote help line
	 */
	public String getFormatCommandsVoteHelpLine() {

		String str = getData().getString("Format.Commands.Vote.Help.Line");
		if (str != null) {
			return str;
		} else {
			return "&3&l%Command% - &3%HelpMessage%";
		}

	}

	/**
	 * Gets the commands vote help require permission.
	 *
	 * @return the commands vote help require permission
	 */
	public boolean getFormatCommandsVoteHelpRequirePermission() {
		return getData().getBoolean("Format.Commands.Vote.Help.RequirePermission");
	}

	/**
	 * Gets the commands vote help title.
	 *
	 * @return the commands vote help title
	 */
	public String getFormatCommandsVoteHelpTitle() {
		String str = getData().getString("Format.Commands.Vote.Help.Title");
		if (str != null) {
			return str;
		} else {
			return "Voting Player Help";
		}

	}

	/**
	 * Gets the commands vote last line.
	 *
	 * @return the commands vote last line
	 */
	public String getFormatCommandsVoteLastLine() {
		String str = getData().getString("Format.Commands.Vote.Last.Line");
		if (str != null) {
			return str;
		} else {
			return "&3%SiteName%: &6%time%";
		}
	}

	/**
	 * Gets the commands vote last title.
	 *
	 * @return the commands vote last title
	 */
	public String getFormatCommandsVoteLastTitle() {
		String str = getData().getString("Format.Commands.Vote.Last.Title");
		if (str != null) {
			return str;
		} else {
			return "&3&l%player% Last Vote Times:";
		}
	}

	/**
	 * Gets the commands vote next info can vote.
	 *
	 * @return the commands vote next info can vote
	 */
	public String getFormatCommandsVoteNextInfoCanVote() {
		String str = getData().getString("Format.Commands.Vote.Next.Info.CanVote");
		if (str != null) {
			return str;
		} else {
			return "Go Vote!";
		}
	}

	/**
	 * Gets the commands vote next info error.
	 *
	 * @return the commands vote next info error
	 */
	public String getFormatCommandsVoteNextInfoError() {
		String str = getData().getString("Format.Commands.Vote.Next.Info.Error");
		if (str != null) {
			return str;
		} else {
			return "";
		}
	}

	/**
	 * Gets the commands vote next info time.
	 *
	 * @return the commands vote next info time
	 */
	public String getFormatCommandsVoteNextInfoTime() {
		String str = getData().getString("Format.Commands.Vote.Next.Info.TimeUntilVote");
		if (str != null) {
			return str;
		} else {
			return "&cCould not caculate time until next vote!";
		}
	}

	/**
	 * Gets the commands vote next layout.
	 *
	 * @return the commands vote next layout
	 */
	public String getFormatCommandsVoteNextLayout() {
		String str = getData().getString("Format.Commands.Vote.Next.Layout");
		if (str != null) {
			return str;
		} else {
			return "&3%SiteName%: &6%info%";
		}
	}

	/**
	 * Gets the commands vote next title.
	 *
	 * @return the commands vote next title
	 */
	public String getFormatCommandsVoteNextTitle() {
		String str = getData().getString("Format.Commands.Vote.Next.Title");
		if (str != null) {
			return str;
		} else {
			return "&3&l%player% Next Votes:";
		}
	}

	/**
	 * Gets the commands vote party.
	 *
	 * @return the commands vote party
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getFormatCommandsVoteParty() {
		try {
			ArrayList<String> list = (ArrayList<String>) getData().getList("Format.Commands.Vote.Party");
			if (list != null) {
				return list;
			}
			ArrayList<String> msg = new ArrayList<String>();
			msg.add("&cCurrently at &6%Votes%&c, &6%NeededVotes% &cmore votes to go to reach &6%VotesRequired%");
			return msg;
		} catch (Exception ex) {
			ArrayList<String> msg = new ArrayList<String>();
			msg.add("&cCurrently at &6%Votes%&c, &6%NeededVotes% &cmore votes to go to reach &6%VotesRequired%");
			return msg;
		}
	}

	/**
	 * Gets the commands vote text.
	 *
	 * @return the commands vote text
	 */
	@SuppressWarnings("unchecked")
	public List<String> getFormatCommandsVoteText() {
		List<String> str;
		try {
			str = (List<String>) getData().getList("Format.Commands.Vote.Text");
			if (str != null) {
				return str;
			} else {
				str = new ArrayList<String>();
				str.add("&4&lVote for our server!");
				return str;
			}
		} catch (Exception ex) {
			str = new ArrayList<String>();
			str.add("&4&lVote for our server!");
			return str;
		}
	}

	/**
	 * Gets the commands vote total all line.
	 *
	 * @return the commands vote total all line
	 */
	public String getFormatCommandsVoteTotalAllLine() {
		String str = getData().getString("Format.Commands.Vote.TotalAll.Line");
		if (str != null) {
			return str;
		} else {
			return "&3%SiteName% &6%Total%";
		}
	}

	/**
	 * Gets the commands vote total all title.
	 *
	 * @return the commands vote total all title
	 */
	public String getFormatCommandsVoteTotalAllTitle() {
		String str = getData().getString("Format.Commands.Vote.TotalAll.Title");
		if (str != null) {
			return str;
		} else {
			return "&3&lAll Votes Total:";
		}
	}

	/**
	 * Gets the commands vote total all total.
	 *
	 * @return the commands vote total all total
	 */
	public String getFormatCommandsVoteTotalAllTotal() {
		String str = getData().getString("Format.Commands.Vote.TotalAll.Total");
		if (str != null) {
			return str;
		} else {
			return "&3&lTotal: &6&l%Totals%";
		}
	}

	/**
	 * Gets the commands vote total line.
	 *
	 * @return the commands vote total line
	 */
	public String getFormatCommandsVoteTotalLine() {
		String str = getData().getString("Format.Commands.Vote.Total.Line");
		if (str != null) {
			return str;
		} else {
			return "&3%SiteName%: &6%Total%";
		}
	}

	/**
	 * Gets the commands vote total title.
	 *
	 * @return the commands vote total title
	 */
	public String getFormatCommandsVoteTotalTitle() {
		String str = getData().getString("Format.Commands.Vote.Total.Title");
		if (str != null) {
			return str;
		} else {
			return "&3&l%player% Total Votes:";
		}
	}

	/**
	 * Gets the commands vote total total.
	 *
	 * @return the commands vote total total
	 */
	public String getFormatCommandsVoteTotalTotal() {
		String str = getData().getString("Format.Commands.Vote.Total.Total");
		if (str != null) {
			return str;
		} else {
			return "&3&lTotal: &6&l%Totals%";
		}
	}

	/**
	 * Gets the commands vote URLS.
	 *
	 * @return the commands vote URLS
	 */
	public String getFormatCommandsVoteURLS() {
		String str = getData().getString("Format.Commands.Vote.Sites");
		if (str != null) {
			return str;
		} else {
			return "&4%num%: &c&l%SiteName% - &c%url%";
		}
	}

	/**
	 * Gets the command vote points.
	 *
	 * @return the command vote points
	 */
	public String getFormatCommandVotePoints() {
		String str = getData().getString("Format.Commands.Vote.Points");
		if (str != null) {
			return str;
		} else {
			return "&a%Player% currently has &a&l%Points%&a Points!";
		}
	}

	/**
	 * Gets the command vote top line.
	 *
	 * @return the command vote top line
	 */
	public String getFormatCommandVoteTopLine() {
		String str = getData().getString("Format.Commands.Vote.Top.Line");
		if (str != null) {
			return str;
		} else {
			return "&c%num%: &6%player%, %votes%";
		}
	}

	/**
	 * Gets the command vote top title.
	 *
	 * @return the command vote top title
	 */
	public String getFormatCommandVoteTopTitle() {
		String str = getData().getString("Format.Commands.Vote.Top.Title");
		if (str != null) {
			return str;
		} else {
			return "&3Top Voters %page%/%maxpages%";
		}
	}

	/**
	 * Gets the broad cast msg.
	 *
	 * @return the broad cast msg
	 */
	public String getFormatBroadCastMsg() {
		String str = getData().getString("Format.BroadcastMsg");
		if (str != null) {
			return str;
		} else {
			return "&6[&4Broadcast&6] &2Thanks &c%player% &2for voting on %SiteName%";
		}
	}

	/**
	 * Gets the broadcast when online.
	 *
	 * @return the broadcast when online
	 */
	public boolean getFormatBroadcastWhenOnline() {
		return getData().getBoolean("Format.BroadcastWhenOnline");
	}

	/**
	 * Gets the format help line.
	 *
	 * @return the format help line
	 */
	public String getFormatHelpLine() {
		return getData().getString("Format.HelpLine", "&3&l%Command% - &3%HelpMessage%");
	}

	/**
	 * Gets the format no perms.
	 *
	 * @return the format no perms
	 */
	public String getFormatNoPerms() {
		return getData().getString("Format.NoPerms", "&cYou do not have enough permission!");
	}

	/**
	 * Gets the login msg.
	 *
	 * @return the login msg
	 */
	public String getFormatLoginMsg() {
		String str = getData().getString("Format.LoginMsg");
		if (str != null) {
			return str;
		} else {
			return "&cRemember to vote!";
		}
	}

	/**
	 * Gets the format not number.
	 *
	 * @return the format not number
	 */
	public String getFormatNotNumber() {
		return getData().getString("Format.NotNumber", "&cError on &6%arg%&c, number expected!");
	}

	/**
	 * Gets the page size.
	 *
	 * @return the page size
	 */
	public int getFormatPageSize() {
		return 10;
	}

	public String getFormatShopFailedMsg() {
		String msg = getData().getString("Format.ShopFailed", "&cYou do not have %Points% points to purhcase this!");

		return msg;

	}

	public String getFormatShopPurchaseMsg() {
		String msg = getData().getString("Format.ShopPurchase", "&aYou bought the %Identifier% for %Points% Points!");

		return msg;

	}

	/**
	 * Gets the sign top voter sign line 1.
	 *
	 * @return the sign top voter sign line 1
	 */
	public String getFormatSignTopVoterSignLine1() {
		String str = getData().getString("Format.Signs.TopVoterSign.Line1");
		if (str != null) {
			return str;
		} else {
			return "TopVoter: %SiteName%";
		}
	}

	/**
	 * Gets the sign top voter sign line 2.
	 *
	 * @return the sign top voter sign line 2
	 */
	public String getFormatSignTopVoterSignLine2() {
		String str = getData().getString("Format.Signs.TopVoterSign.Line2");
		if (str != null) {
			return str;
		} else {
			return "#%position%";
		}
	}

	/**
	 * Gets the sign top voter sign line 3.
	 *
	 * @return the sign top voter sign line 3
	 */
	public String getFormatSignTopVoterSignLine3() {
		String str = getData().getString("Format.Signs.TopVoterSign.Line3");
		if (str != null) {
			return str;
		} else {
			return "%player%";
		}
	}

	/**
	 * Gets the sign top voter sign line 4.
	 *
	 * @return the sign top voter sign line 4
	 */
	public String getFormatSignTopVoterSignLine4() {
		String str = getData().getString("Format.Signs.TopVoterSign.Line4");
		if (str != null) {
			return str;
		} else {
			return "%votes% Votes";
		}
	}

	/**
	 * Gets the sign top voter sign right click message.
	 *
	 * @return the sign top voter sign right click message
	 */
	public String getFormatSignTopVoterSignRightClickMessage() {
		String str = getData().getString("Format.Signs.RightClickMessage");
		if (str != null) {
			return str;
		} else {
			return "&c&l%player% &cis &c&l%position% &cwith &c&l%votes% &cin &c&l%SiteName%";
		}
	}

	/**
	 * Gets the time format.
	 *
	 * @return the time format
	 */
	public String getFormatTimeFormat() {
		String string = getData().getString("Format.TimeFormat");
		if (string == null) {
			return "EEE, d MMM yyyy HH:mm";
		}
		return string;
	}

	/**
	 * Gets the top voter reward msg.
	 *
	 * @return the top voter reward msg
	 */
	public String getFormatTopVoterRewardMsg() {
		String msg = getData().getString("Format.TopVoterAwardMsg");
		if (msg != null) {
			return msg;
		} else {
			return "&aYou came in %place% in top voters of the month! Here is an award!";
		}
	}

	/**
	 * Gets the vote help.
	 *
	 * @return the vote help
	 */
	@SuppressWarnings("unchecked")
	public List<String> getFormatVoteHelp() {
		return (List<String>) getData().getList("Format.Commands.Vote.Help.Lines");
	}

	public Set<String> getIdentifiers() {
		return getData().getConfigurationSection("Shop").getKeys(false);
	}

	public ConfigurationSection getIdentifierSection(String identifier) {
		return getData().getConfigurationSection("Shop." + identifier);
	}

	public int getIdentifierItemAmount(String identifier) {
		return getData().getInt("Shop." + identifier + ".Item.Amount");
	}

	public int getIdentifierCost(String identifier) {
		return getData().getInt("Shop." + identifier + ".Cost");
	}

	public int getIdentifierSlot(String identifier) {
		return getData().getInt("Shop." + identifier + ".Slot");
	}

	@SuppressWarnings("unchecked")
	public ArrayList<String> getIdentifierRewards(String identifier) {
		try {
			ArrayList<String> list = (ArrayList<String>) getData().getList("Shop." + identifier + ".Rewards");
			if (list != null) {
				return list;
			}
			return new ArrayList<String>();
		} catch (Exception ex) {
			return new ArrayList<String>();
		}
	}

	public String getIdentifierFromSlot(int slot) {
		for (String identifier : getIdentifiers()) {
			if (getIdentifierSlot(identifier) == slot) {
				return identifier;
			}
		}
		return null;
	}

	/**
	 * Gets the vote GUI slot command.
	 *
	 * @param slot
	 *            the slot
	 * @return the vote GUI slot command
	 */

	public String getVoteGUISlotCommand(String slot) {
		return getData().getString("GUI.VoteGUI." + slot + ".Command", "");
	}

	public ConfigurationSection getVoteGUISlotSection(String slot) {
		return getData().getConfigurationSection("GUI.VoteGUI." + slot + ".Item");
	}

	/**
	 * Gets the vote GUI slot lore.
	 *
	 * @param slot
	 *            the slot
	 * @return the vote GUI slot lore
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getVoteGUISlotLore(String slot) {

		ArrayList<String> list = (ArrayList<String>) getData().getList("GUI.VoteGUI." + slot + ".Lore");
		if (list != null) {
			return list;
		}
		return new ArrayList<String>();
	}

	/**
	 * Gets the vote GUI slots.
	 *
	 * @return the vote GUI slots
	 */
	public Set<String> getVoteGUISlots() {
		try {
			return getData().getConfigurationSection("GUI.VoteGUI").getKeys(false);
		} catch (Exception ex) {
			return new HashSet<String>();
		}
	}

	/**
	 * Gets the vote GUI slot slot.
	 *
	 * @param slot
	 *            the slot
	 * @return the vote GUI slot slot
	 */
	public int getVoteGUISlotSlot(String slot) {
		return getData().getInt("GUI.VoteGUI." + slot + ".Slot");
	}

	public ConfigurationSection getVoteSiteItemSection(String site) {
		String siteName = site.replace(".", "-");
		return getData().getConfigurationSection("GUI.VoteReward." + siteName);
	}

	/**
	 * Gets the vote site items.
	 *
	 * @param site
	 *            the site
	 * @return the vote site items
	 */
	public Set<String> getVoteSiteItems(String site) {
		String siteName = site.replace(".", "-");
		try {
			return getData().getConfigurationSection("GUI.VoteReward." + siteName + ".Items").getKeys(false);
		} catch (Exception ex) {
			return new HashSet<String>();
		}
	}

	public ConfigurationSection getVoteSiteItemsSection(String site, String item) {
		String siteName = site.replace(".", "-");
		return getData().getConfigurationSection("GUI.VoteReward." + siteName + ".Items." + item);
	}

	/**
	 * Gets the vote site items slot.
	 *
	 * @param site
	 *            the site
	 * @param item
	 *            the item
	 * @return the vote site items slot
	 */
	public int getVoteSiteItemsSlot(String site, String item) {
		String siteName = site.replace(".", "-");
		return getData().getInt("GUI.VoteReward." + siteName + ".Items." + item + ".Slot");
	}

	public ConfigurationSection getVoteURLAlreadyVotedItemSection() {
		return getData().getConfigurationSection("GUI.VoteURL.AlreadyVotedItem");
	}

	public ConfigurationSection getVoteURLCanVoteItemSection() {
		return getData().getConfigurationSection("GUI.VoteURL.CanVoteItem");
	}

	/**
	 * Gets the vote URL next vote.
	 *
	 * @return the vote URL next vote
	 */
	public String getVoteURLNextVote() {
		String str = getData().getString("GUI.VoteURL.NextVote");
		if (str != null) {
			return str;
		} else {
			return "&cCan Vote In: %Info%";
		}
	}

	/**
	 * Gets the vote URL see URL.
	 *
	 * @return the vote URL see URL
	 */
	public String getVoteURLSeeURL() {
		String str = getData().getString("GUI.VoteURL.SeeURL");
		if (str != null) {
			return str;
		} else {
			return "&cClick to see URL";
		}
	}

	/**
	 * Gets the vote URL site name.
	 *
	 * @return the vote URL site name
	 */
	public String getVoteURLSiteName() {
		String str = getData().getString("GUI.VoteURL.SiteName");
		if (str != null) {
			return str;
		} else {
			return "&c%Name%";
		}
	}

	/**
	 * Gets the vote URL view all urls button enabled.
	 *
	 * @return the vote URL view all urls button enabled
	 */
	public boolean getVoteURLViewAllUrlsButtonEnabled() {
		return getData().getBoolean("GUI.VoteURL.ViewAllUrlsButtonEnabled");
	}

	/**
	 * Gets the all sites reward.
	 *
	 * @return the all sites reward
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getAllSitesReward() {
		try {
			return (ArrayList<String>) getData().getList("AllSites");
		} catch (Exception ex) {
			return new ArrayList<String>();
		}
	}

	/**
	 * Gets the cumulative reward enabled.
	 *
	 * @param cumulative
	 *            the cumulative
	 * @return the cumulative reward enabled
	 */
	public boolean getCumulativeRewardEnabled(int cumulative) {
		return getData().getBoolean("Cumulative." + cumulative + ".Enabled");
	}

	/**
	 * Gets the cumulative rewards.
	 *
	 * @param cumulative
	 *            the cumulative
	 * @return the cumulative rewards
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getCumulativeRewards(int cumulative) {
		try {
			ArrayList<String> list = (ArrayList<String>) getData().getList("Cumulative." + cumulative + ".Rewards");
			if (list != null) {
				return list;
			}

			return new ArrayList<String>();
		} catch (Exception ex) {
			return new ArrayList<String>();
		}
	}

	/**
	 * Gets the cumulative votes.
	 *
	 * @return the cumulative votes
	 */
	public Set<String> getCumulativeVotes() {
		try {
			Set<String> set = getData().getConfigurationSection("Cumulative").getKeys(false);
			if (set != null) {
				return set;
			}
			return new HashSet<String>();
		} catch (Exception ex) {
			return new HashSet<String>();
		}
	}

	/**
	 * Gets the cumulative votes in same day.
	 *
	 * @param cumulative
	 *            the cumulative
	 * @return the cumulative votes in same day
	 */
	public boolean getCumulativeVotesInSameDay(int cumulative) {
		return getData().getBoolean("Cumulative." + cumulative + ".VotesInSameDay");
	}

	public boolean getCumulativeVotesInSameWeek(int cumulative) {
		return getData().getBoolean("Cumulative." + cumulative + ".VotesInSameWeek");
	}

	/**
	 * Gets the first vote rewards.
	 *
	 * @return the first vote rewards
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getFirstVoteRewards() {
		try {
			return (ArrayList<String>) getData().getList("FirstVote");
		} catch (Exception ex) {
			return new ArrayList<String>();
		}
	}

	/**
	 * Gets the rewards.
	 *
	 * @return the rewards
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getAnySiteRewards() {
		return (ArrayList<String>) getData().getList("AnySiteRewards", new ArrayList<String>());

	}

	/**
	 * Sets the rewards.
	 *
	 * @param rewards
	 *            the new rewards
	 */
	public void setAnySiteRewards(ArrayList<String> rewards) {
		getData().set("AnySiteRewards", rewards);
		saveData();
	}

	/**
	 * Gets the milestone reward enabled.
	 *
	 * @param milestones
	 *            the milestones
	 * @return the milestone reward enabled
	 */
	public boolean getMilestoneRewardEnabled(int milestones) {
		return getData().getBoolean("MileStones." + milestones + ".Enabled");
	}

	/**
	 * Gets the milestone rewards.
	 *
	 * @param milestones
	 *            the milestones
	 * @return the milestone rewards
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getMilestoneRewards(int milestones) {
		return (ArrayList<String>) getData().getList("MileStones." + milestones + ".Rewards", new ArrayList<String>());
	}

	public boolean getMilestoneResetMonthly(int milestones) {
		return getData().getBoolean("MileStones." + milestones + ".ResetMonthly");
	}

	/**
	 * Gets the milestone votes.
	 *
	 * @return the milestone votes
	 */
	public Set<String> getMilestoneVotes() {
		try {
			Set<String> set = getData().getConfigurationSection("MileStones").getKeys(false);
			if (set != null) {
				return set;
			}
			return new HashSet<String>();
		} catch (Exception ex) {
			return new HashSet<String>();
		}
	}

	/**
	 * Gets the min votes enabled.
	 *
	 * @return the min votes enabled
	 */
	public boolean getMinVotesEnabled() {
		return getData().getBoolean("MinVotes.Enabled");
	}

	/**
	 * Gets the min votes rewards.
	 *
	 * @return the min votes rewards
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getMinVotesRewards() {
		return (ArrayList<String>) getData().getList("MinVotes.Rewards", new ArrayList<String>());
	}

	/**
	 * Gets the min votes votes.
	 *
	 * @return the min votes votes
	 */
	public int getMinVotesVotes() {
		return getData().getInt("MinVotes.Votes");
	}

	/**
	 * Gets the vote party enabled.
	 *
	 * @return the vote party enabled
	 */
	public boolean getVotePartyEnabled() {
		return getData().getBoolean("VoteParty.Enabled");
	}

	public boolean getVotePartyResetEachDay() {
		return getData().getBoolean("VoteParty.ResetEachDay");
	}

	/**
	 * Gets the vote party give all players.
	 *
	 * @return the vote party give all players
	 */
	public boolean getVotePartyGiveAllPlayers() {
		return getData().getBoolean("VoteParty.GiveAllPlayers");
	}

	/**
	 * Gets the vote party rewards.
	 *
	 * @return the vote party rewards
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getVotePartyRewards() {
		return (ArrayList<String>) getData().getList("VoteParty.Rewards");
	}

	/**
	 * Gets the vote party votes required.
	 *
	 * @return the vote party votes required
	 */
	public int getVotePartyVotesRequired() {
		return getData().getInt("VoteParty.VotesRequired");
	}

	public int getUserVotesRequired() {
		return getData().getInt("VoteParty.UserVotesRequired");
	}

	/**
	 * Gets the votes required.
	 *
	 * @return the votes required
	 */
	public int getVotesRequired() {
		return getData().getInt("VotesRequired");
	}

	/**
	 * Sets the cumulative rewards.
	 *
	 * @param cumulative
	 *            the cumulative
	 * @param rewards
	 *            the rewards
	 */
	public void setCumulativeRewards(int cumulative, ArrayList<String> rewards) {
		getData().set("Cumulative." + cumulative + ".Rewards", rewards);
		saveData();
	}

	/**
	 * Gets the black list.
	 *
	 * @return the black list
	 */
	@SuppressWarnings("unchecked")
	public List<String> getBlackList() {
		return (List<String>) getData().getList("BlackList");
	}

	/**
	 * Gets the daily award rewards.
	 *
	 * @param pos
	 *            the pos
	 * @return the daily award rewards
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getDailyAwardRewards(int pos) {
		return (ArrayList<String>) getData().getList("DailyAwards." + pos + ".Rewards");
	}

	/**
	 * Gets the daily awards enabled.
	 *
	 * @return the daily awards enabled
	 */
	public boolean getDailyAwardsEnabled() {
		return getData().getBoolean("EnableDailyAwards");
	}

	/**
	 * Gets the daily possible reward places.
	 *
	 * @return the daily possible reward places
	 */
	public Set<String> getDailyPossibleRewardPlaces() {
		try {
			return getData().getConfigurationSection("DailyAwards").getKeys(false);
		} catch (Exception ex) {
			Set<String> noValues = new HashSet<String>();
			return noValues;
		}
	}

	/**
	 * Gets the monthly award rewards.
	 *
	 * @param pos
	 *            the pos
	 * @return the monthly award rewards
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getMonthlyAwardRewards(int pos) {
		return (ArrayList<String>) getData().getList("MonthlyAwards." + pos + ".Rewards");
	}

	/**
	 * Gets the monthly awards enabled.
	 *
	 * @return the monthly awards enabled
	 */
	public boolean getMonthlyAwardsEnabled() {
		return getData().getBoolean("EnableMonthlyAwards");
	}

	/**
	 * Gets the monthly possible reward places.
	 *
	 * @return the monthly possible reward places
	 */
	public Set<String> getMonthlyPossibleRewardPlaces() {
		try {
			return getData().getConfigurationSection("MonthlyAwards").getKeys(false);
		} catch (Exception ex) {
			Set<String> noValues = new HashSet<String>();
			return noValues;
		}
	}

	/**
	 * Gets the store top voters daily.
	 *
	 * @return the store top voters daily
	 */
	public boolean getStoreTopVotersDaily() {
		return getData().getBoolean("StoreTopVoters.Daily");
	}

	/**
	 * Gets the store top voters monthly.
	 *
	 * @return the store top voters monthly
	 */
	public boolean getStoreTopVotersMonthly() {
		return getData().getBoolean("StoreTopVoters.Monthly");
	}

	/**
	 * Gets the store top voters weekly.
	 *
	 * @return the store top voters weekly
	 */
	public boolean getStoreTopVotersWeekly() {
		return getData().getBoolean("StoreTopVoters.Weekly");
	}

	/**
	 * Gets the weekly award rewards.
	 *
	 * @param pos
	 *            the pos
	 * @return the weekly award rewards
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getWeeklyAwardRewards(int pos) {
		return (ArrayList<String>) getData().getList("WeeklyAwards." + pos + ".Rewards");
	}

	/**
	 * Gets the weekly awards enabled.
	 *
	 * @return the weekly awards enabled
	 */
	public boolean getWeeklyAwardsEnabled() {
		return getData().getBoolean("EnableWeeklyAwards");
	}

	/**
	 * Gets the weekly possible reward places.
	 *
	 * @return the weekly possible reward places
	 */
	public Set<String> getWeeklyPossibleRewardPlaces() {
		try {
			return getData().getConfigurationSection("WeeklyAwards").getKeys(false);
		} catch (Exception ex) {
			Set<String> noValues = new HashSet<String>();
			return noValues;
		}
	}

	/**
	 * Gets the enabled.
	 *
	 * @return the enabled
	 */
	public boolean getVoteRemindingEnabled() {
		return getData().getBoolean("VoteReminding.Enabled");
	}

	public void setVoteRemindingEnabled(boolean value) {
		getData().set("VoteReminding.Enabled", value);
		saveData();
	}

	/**
	 * Gets the remind delay.
	 *
	 * @return the remind delay
	 */
	public int getVoteRemindingRemindDelay() {
		return getData().getInt("VoteReminding.RemindDelay", 30);
	}

	public void setVoteRemindingRemindDelay(int value) {
		getData().set("VoteReminding.RemindDelay", value);
		saveData();
	}

	/**
	 * Gets the remind on login.
	 *
	 * @return the remind on login
	 */
	public boolean getVoteRemindingRemindOnLogin() {
		return getData().getBoolean("VoteReminding.RemindOnLogin");
	}

	public void setVoteRemindingRemindOnLogin(boolean value) {
		getData().set("VoteReminding.RemindOnLogin", value);
		saveData();
	}

	/**
	 * Gets the remind only once.
	 *
	 * @return the remind only once
	 */
	public boolean getVoteRemindingRemindOnlyOnce() {
		return getData().getBoolean("VoteReminding.RemindOnlyOnce");
	}

	public void setVoteRemindingRemindOnlyOnce(boolean value) {
		getData().set("VoteReminding.RemindOnlyOnce", value);
		saveData();
	}

	/**
	 * Gets the rewards.
	 *
	 * @return the rewards
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getVoteRemindingRewards() {
		return (ArrayList<String>) getData().getList("VoteReminding.Rewards", new ArrayList<String>());
	}

	public void setVoteRemindingRewards(ArrayList<String> value) {
		getData().set("VoteReminding.Rewards", value);
		saveData();
	}

	/**
	 * Allow un joined.
	 *
	 * @return true, if successful
	 */
	public boolean allowUnJoined() {
		return getData().getBoolean("AllowUnjoined");
	}

	/**
	 * Gets the auto create vote sites.
	 *
	 * @return the auto create vote sites
	 */
	public boolean getAutoCreateVoteSites() {
		return getData().getBoolean("AutoCreateVoteSites");
	}

	/**
	 * Gets the broad cast votes enabled.
	 *
	 * @return the broad cast votes enabled
	 */
	public boolean getBroadCastVotesEnabled() {
		return getData().getBoolean("BroadcastVote");
	}

	/**
	 * Gets the log votes to file.
	 *
	 * @return the log votes to file
	 */
	public boolean getLogVotesToFile() {
		return getData().getBoolean("LogVotesToFile");
	}

	/**
	 * Gets the send scoreboards.
	 *
	 * @return the send scoreboards
	 */
	public boolean getSendScoreboards() {
		return getData().getBoolean("SendScoreboards");
	}

	/**
	 * Sets the allow un joined.
	 *
	 * @param value
	 *            the new allow un joined
	 */
	public void setAllowUnJoined(boolean value) {
		getData().set("AllowUnjoined", value);
		saveData();
	}

	/**
	 * Sets the broadcast vote enabled.
	 *
	 * @param value
	 *            the new broadcast vote enabled
	 */
	public void setBroadcastVoteEnabled(boolean value) {
		getData().set("BroadcastVote", value);
		saveData();
	}

	/**
	 * Sets the debug enabled.
	 *
	 * @param value
	 *            the new debug enabled
	 */
	public void setDebugEnabled(boolean value) {
		getData().set("Debug", value);
		saveData();
	}

	/**
	 * Sets the debug info ingame.
	 *
	 * @param value
	 *            the new debug info ingame
	 */
	public void setDebugInfoIngame(boolean value) {
		getData().set("DebugInfoIngame", value);
		saveData();
	}

	/**
	 * Sets the top voter awards enabled.
	 *
	 * @param value
	 *            the new top voter awards enabled
	 */
	public void setTopVoterAwardsEnabled(boolean value) {
		getData().set("TopVoterAwards", value);
		saveData();
	}

	@Override
	public void onFileCreation() {
		plugin.saveResource("Config.yml", true);
	}

	public boolean getCommandsUseGUIToday() {
		return getData().getBoolean("Commands.UseGUI.Today", true);
	}

	public boolean getCommandsUseGUITotal() {
		return getData().getBoolean("Commands.UseGUI.Total", true);
	}

	public boolean getCommandsUseGUINext() {
		return getData().getBoolean("Commands.UseGUI.Next", true);
	}

	public boolean getCommandsUseGUITopVoter() {
		return getData().getBoolean("Commands.UseGUI.TopVoter", true);
	}

	public boolean getCommandsUseGUILast() {
		return getData().getBoolean("Commands.UseGUI.Last", true);
	}

	public boolean getCommandsUseGUIVote() {
		return getData().getBoolean("Commands.UseGUI.Vote", true);
	}

}