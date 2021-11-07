package com.bencodez.votingplugin.specialrewards;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;

import org.bukkit.Bukkit;

import com.bencodez.advancedcore.api.messages.StringParser;
import com.bencodez.advancedcore.api.rewards.RewardBuilder;
import com.bencodez.advancedcore.api.rewards.RewardHandler;
import com.bencodez.advancedcore.api.rewards.RewardOptions;
import com.bencodez.votingplugin.VotingPluginMain;
import com.bencodez.votingplugin.bungee.BungeeMessageData;
import com.bencodez.votingplugin.events.PlayerSpecialRewardEvent;
import com.bencodez.votingplugin.events.SpecialRewardType;
import com.bencodez.votingplugin.topvoter.TopVoter;
import com.bencodez.votingplugin.user.VotingPluginUser;

// TODO: Auto-generated Javadoc
/**
 * The Class OtherVoteReward.
 */
public class SpecialRewards {

	private VotingPluginMain plugin;

	public SpecialRewards(VotingPluginMain plugin) {
		this.plugin = plugin;
	}

	public boolean checkAllSites(VotingPluginUser user, boolean forceBungee) {
		boolean checkAllVotes = user.checkAllVotes();
		if (checkAllVotes
				&& (plugin.getConfigFile().isExtraAllSitesCheck() || plugin.getBungeeSettings().isUseBungeecoord())) {
			int currentDay = LocalDateTime.now().getDayOfYear();
			int day = user.getGottenAllSitesDay();
			if (currentDay == day) {
				checkAllVotes = false;
				plugin.debug("Not giving allsites, already gotten today");
			} else {
				user.setGottenAllSitesDay(currentDay);
			}

		}
		if (checkAllVotes) {
			giveAllSitesRewards(user, user.isOnline(), forceBungee);
		}
		return checkAllVotes;
	}

	public boolean checkCumualativeVotes(VotingPluginUser user, BungeeMessageData bungeeMessageData,
			boolean forceBungee) {
		boolean gotCumulativeAny = false;
		Set<String> votes = plugin.getSpecialRewardsConfig().getCumulativeVotes();
		for (String vote : votes) {
			if (StringParser.getInstance().isInt(vote)) {
				int votesRequired = Integer.parseInt(vote);
				if (votesRequired != 0) {
					if (plugin.getSpecialRewardsConfig().getCumulativeRewardEnabled(votesRequired)
							&& RewardHandler.getInstance().hasRewards(plugin.getSpecialRewardsConfig().getData(),
									plugin.getSpecialRewardsConfig().getCumulativeRewardsPath(votesRequired))) {
						boolean gotCumulative = false;
						int total = 0;
						boolean useBungeeTotalNum = bungeeMessageData != null;
						String totalToUse = plugin.getSpecialRewardsConfig().getCumulativeVotesTotal(votesRequired);
						TopVoter top = TopVoter.getTopVoter(totalToUse);
						if (!useBungeeTotalNum) {
							total = user.getTotal(top);
						} else {
							switch (top) {
							case AllTime:
								total = bungeeMessageData.getAllTimeTotal();
								break;
							case Daily:
								total = bungeeMessageData.getDailyTotal();
								break;
							case Monthly:
								total = bungeeMessageData.getMonthTotal();
								break;
							case Weekly:
								total = bungeeMessageData.getWeeklyTotal();
								break;
							default:
								break;

							}
						}

						if (total != 0 && total >= votesRequired) {
							if ((total % votesRequired) == 0) {
								gotCumulative = true;
							}
						}

						if (gotCumulative) {
							if (!plugin.getSpecialRewardsConfig().isOnlyOneCumulative() || !gotCumulativeAny) {
								gotCumulativeAny = true;
								plugin.debug(user.getPlayerName() + " got cumulative " + votesRequired + ", total: "
										+ top.toString() + ", current total: " + total);
								giveCumulativeVoteReward(user, user.isOnline(), votesRequired, forceBungee);
							} else {
								plugin.debug("Already got one cumulative");
							}
						} else {
							plugin.devDebug(user.getPlayerName() + " not able to get cumulative " + votesRequired
									+ ", total required: " + top.toString() + ", current player total: " + total);
						}
					}
				}
			} else {
				plugin.debug("Invalid cumulative number: " + vote);
			}
		}
		return gotCumulativeAny;
	}

	public boolean checkFirstVote(VotingPluginUser user, boolean forceBungee) {
		if (RewardHandler.getInstance().hasRewards(plugin.getSpecialRewardsConfig().getData(),
				plugin.getSpecialRewardsConfig().getFirstVoteRewardsPath())) {
			if (!user.hasGottenFirstVote()) {
				giveFirstVoteRewards(user, user.isOnline(), forceBungee);
				return true;
			}

		}
		return false;
	}

	public boolean checkFirstVoteToday(VotingPluginUser user, boolean forceBungee) {
		if (RewardHandler.getInstance().hasRewards(plugin.getSpecialRewardsConfig().getData(),
				plugin.getSpecialRewardsConfig().getFirstVoteRewardsPath())) {
			if (!user.hasGottenFirstVoteToday()) {
				giveFirstVoteTodayRewards(user, user.isOnline(), forceBungee);
				return true;
			}

		}
		return false;
	}

	public boolean checkMilestone(VotingPluginUser user, BungeeMessageData bungeeMessageData, boolean forceBungee) {
		int milestoneCount = user.getMilestoneCount();
		if (bungeeMessageData != null) {
			try {
				milestoneCount = bungeeMessageData.getMilestoneCount();
			} catch (Exception e) {
				e.printStackTrace();
				milestoneCount = user.getMilestoneCount();
			}
		}
		if (plugin.getConfigFile().isPreventRepeatMilestones()) {
			ArrayList<Integer> nums = new ArrayList<Integer>();

			for (String str : plugin.getSpecialRewardsConfig().getMilestoneVotes()) {
				try {
					nums.add(Integer.parseInt(str));
				} catch (Exception e) {
					plugin.getLogger().warning("Failed to get number from " + str);
				}
			}

			for (int num : nums) {
				if (milestoneCount > num) {
					if (!user.hasGottenMilestone(num)) {
						plugin.getLogger()
								.info("Milestone " + num + " for " + user.getPlayerName()
										+ " not already given when it should be, Current AllTimeTotal: "
										+ user.getTotal(TopVoter.AllTime) + ", Current MileStoneCount: "
										+ user.getMilestoneCount());
						user.setHasGotteMilestone(num, true);
					}
				}
			}
		}

		boolean gotMilestone = false;
		Set<String> votes = plugin.getSpecialRewardsConfig().getMilestoneVotes();
		for (String vote : votes) {
			if (StringParser.getInstance().isInt(vote)) {
				int votesRequired = Integer.parseInt(vote);
				if (votesRequired != 0) {
					if (plugin.getSpecialRewardsConfig().getMilestoneRewardEnabled(votesRequired)
							&& RewardHandler.getInstance().hasRewards(plugin.getSpecialRewardsConfig().getData(),
									plugin.getSpecialRewardsConfig().getMilestoneRewardsPath(votesRequired))) {

						int userVotesTotal = milestoneCount;
						if (userVotesTotal >= votesRequired && !user.hasGottenMilestone(votesRequired)) {
							giveMilestoneVoteReward(user, user.isOnline(), votesRequired, forceBungee);
							user.setHasGotteMilestone(votesRequired, true);
							plugin.debug(user.getPlayerName() + " got milestone " + votesRequired);

						}
					} else {
						plugin.debug("Milestone " + votesRequired + " not enabled or has no rewards");
					}
				}
			} else {
				plugin.debug("Invalid milestone number: " + vote);
			}
		}
		return gotMilestone;
	}

	public boolean checkVoteStreak(VotingPluginUser user, String type, boolean forceBungee) {
		boolean gotReward = false;

		Set<String> streaks = plugin.getSpecialRewardsConfig().getVoteStreakVotes(type);
		for (String streak : streaks) {
			String s = streak.replaceAll("-", "");
			boolean multiple = false;
			if (streak.contains("-")) {
				// multiple
				multiple = true;
			}
			if (StringParser.getInstance().isInt(s)) {
				int streakRequired = Integer.parseInt(s);
				if (streakRequired != 0) {
					if (plugin.getSpecialRewardsConfig().getVoteStreakRewardEnabled(type, streak)
							&& RewardHandler.getInstance().hasRewards(plugin.getSpecialRewardsConfig().getData(),
									plugin.getSpecialRewardsConfig().getVoteStreakRewardsPath(type, "" + streak))) {
						int curStreak = 0;
						if (type.equalsIgnoreCase("day")) {
							curStreak = user.getDayVoteStreak();
						} else if (type.equalsIgnoreCase("week")) {
							curStreak = user.getWeekVoteStreak();
						} else if (type.equalsIgnoreCase("month")) {
							curStreak = user.getMonthVoteStreak();
						}
						if (!multiple) {
							if (curStreak == streakRequired) {
								giveVoteStreakReward(user, user.isOnline(), type, "" + streakRequired, curStreak,
										forceBungee);
								gotReward = true;
								plugin.debug(
										user.getPlayerName() + " got VoteStreak " + streakRequired + " for " + type);
							}
						} else {
							if (curStreak != 0 && curStreak % streakRequired == 0) {
								giveVoteStreakReward(user, user.isOnline(), type, streakRequired + "-", curStreak,
										forceBungee);
								gotReward = true;
								plugin.debug(
										user.getPlayerName() + " got VoteStreak " + streakRequired + "* for " + type);
							}
						}
					}
				}
			}

		}

		return gotReward;

	}

	public void giveAllSitesRewards(VotingPluginUser user, boolean online, boolean forceBungee) {
		PlayerSpecialRewardEvent event = new PlayerSpecialRewardEvent(user, SpecialRewardType.ALLSITE);
		Bukkit.getPluginManager().callEvent(event);

		if (event.isCancelled()) {
			return;
		}
		RewardHandler.getInstance().giveReward(user, plugin.getSpecialRewardsConfig().getData(),
				plugin.getSpecialRewardsConfig().getAllSitesRewardPath(),
				new RewardOptions().setServer(forceBungee).setOnline(online));
	}

	public void giveCumulativeVoteReward(VotingPluginUser user, boolean online, int cumulative, boolean forceBungee) {
		PlayerSpecialRewardEvent event = new PlayerSpecialRewardEvent(user,
				SpecialRewardType.CUMMULATIVE.setAmount(cumulative));
		Bukkit.getPluginManager().callEvent(event);

		if (event.isCancelled()) {
			return;
		}

		new RewardBuilder(plugin.getSpecialRewardsConfig().getData(),
				plugin.getSpecialRewardsConfig().getCumulativeRewardsPath(cumulative)).setServer(forceBungee)
						.setOnline(online).withPlaceHolder("Cumulative", "" + cumulative).send(user);
	}

	public void giveFirstVoteRewards(VotingPluginUser user, boolean online, boolean forceBungee) {
		PlayerSpecialRewardEvent event = new PlayerSpecialRewardEvent(user, SpecialRewardType.FIRSTVOTE);
		Bukkit.getPluginManager().callEvent(event);

		if (event.isCancelled()) {
			return;
		}
		RewardHandler.getInstance().giveReward(user, plugin.getSpecialRewardsConfig().getData(),
				plugin.getSpecialRewardsConfig().getFirstVoteRewardsPath(),
				new RewardOptions().setServer(forceBungee).setOnline(online));
	}

	public void giveFirstVoteTodayRewards(VotingPluginUser user, boolean online, boolean forceBungee) {
		PlayerSpecialRewardEvent event = new PlayerSpecialRewardEvent(user, SpecialRewardType.FIRSTVOTETODAY);
		Bukkit.getPluginManager().callEvent(event);

		if (event.isCancelled()) {
			return;
		}
		RewardHandler.getInstance().giveReward(user, plugin.getSpecialRewardsConfig().getData(),
				plugin.getSpecialRewardsConfig().getFirstVoteTodayRewardsPath(),
				new RewardOptions().setServer(forceBungee).setOnline(online));
	}

	public void giveMilestoneVoteReward(VotingPluginUser user, boolean online, int milestone, boolean forceBungee) {
		PlayerSpecialRewardEvent event = new PlayerSpecialRewardEvent(user,
				SpecialRewardType.MILESTONE.setAmount(milestone));
		Bukkit.getPluginManager().callEvent(event);

		if (event.isCancelled()) {
			return;
		}
		new RewardBuilder(plugin.getSpecialRewardsConfig().getData(),
				plugin.getSpecialRewardsConfig().getMilestoneRewardsPath(milestone)).setOnline(online)
						.withPlaceHolder("Milestone", "" + milestone).setServer(forceBungee).send(user);
	}

	public void giveVoteStreakReward(VotingPluginUser user, boolean online, String type, String string, int votes,
			boolean forceBungee) {
		PlayerSpecialRewardEvent event = new PlayerSpecialRewardEvent(user,
				SpecialRewardType.VOTESTREAK.setType(type).setAmount(votes));
		Bukkit.getPluginManager().callEvent(event);

		if (event.isCancelled()) {
			return;
		}
		new RewardBuilder(plugin.getSpecialRewardsConfig().getData(),
				plugin.getSpecialRewardsConfig().getVoteStreakRewardsPath(type, string)).setOnline(online)
						.withPlaceHolder("Type", type).setServer(forceBungee).withPlaceHolder("Streak", "" + votes)
						.send(user);
	}

}
