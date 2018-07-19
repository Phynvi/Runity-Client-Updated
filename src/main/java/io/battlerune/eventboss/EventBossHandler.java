package io.battlerune.eventboss;

public class EventBossHandler {

	public static EventBossData handle(int npc) {
		for (EventBossData data : EventBossData.values()) {
			if (data.getNpcId() == npc) {
				return data;
			}
		}
		return null;
	}
}
