import java.util.*;

public class wreck {

	public static boolean[][] roadBetween;
	public static int numBuildings, daysToRepair;

	public static void main(String[] args) {
		Scanner br = new Scanner(System.in);

		int numberOfCases = br.nextInt();
		for (int caseOn = 1; caseOn <= numberOfCases; caseOn++) {

			numBuildings = br.nextInt();

			int numRoads = br.nextInt();

			daysToRepair = br.nextInt();

			// Stores for each pair of buildings if the is a road between them
			// or not
			roadBetween = new boolean[numBuildings][numBuildings];

			for (int i = 0; i < numRoads; i++) {
				int building1 = br.nextInt() - 1;
				int building2 = br.nextInt() - 1;
				roadBetween[building1][building2] = true;
				roadBetween[building2][building1] = true;
			}

         boolean[][] reach = new boolean[numBuildings][numBuildings];
         for (int i=0; i<numBuildings; i++)
            for (int j=0; j<numBuildings; j++)
               reach[i][j] = roadBetween[i][j];

         for (int k=0; k<numBuildings; k++)
            for (int i=0; i<numBuildings; i++)
               for (int j=0; j<numBuildings; j++)
                  reach[i][j] |= reach[i][k] && reach[k][j];

         for (int i=0; i<numBuildings; i++)
            for (int j=0; j<numBuildings; j++)
               roadBetween[i][j] &= reach[0][i] && reach[0][j];

			// If there is not at least one more building then the number of
			// days it takes to repair a building he will destroy all the
			// buildings before and have no where to go before one is rebuilt
			if (daysToRepair >= numBuildings) {
				System.out.println("City #" + caseOn + ": NO");
			} else {
				// In order for it to be possible to always have a building to
				// wreck everyday we must find some order of buildings where we
				// start and end at the same building and there are at least one
				// more building then the number of days it take to repair a
				// building
				if (findCycle(0, new boolean[numBuildings], 0, 0)) {
					System.out.println("City #" + caseOn + ": YES");
				} else {
					System.out.println("City #" + caseOn + ": NO");
				}
			}
		}

	}

	// This recursively tries all possible orders of visiting buildings
	public static boolean findCycle(int buildingsDestroyed,
			boolean[] isDestroyed, int last, int first) {
		// We have destroyed at least enough buildings for the first one to be
		// repaired now we just need to check to see if we can go back to it.
		if (buildingsDestroyed >= daysToRepair + 1) {
			if (roadBetween[last][first]) {
				return true;
			}
		}

		if (buildingsDestroyed == 0) {
			// We can choose any building to destroy first.
			for (int i = 0; i < numBuildings; i++) {
				isDestroyed[i] = true;
				if (findCycle(buildingsDestroyed + 1, isDestroyed, i, i)) {
					return true;
				}
				isDestroyed[i] = false;
			}
		} else {
			// Try all buildings to go to next that are not destroyed and have a
			// road connecting them to the last building we destroyed.
			for (int i = 0; i < numBuildings; i++) {
				if (!isDestroyed[i] && roadBetween[last][i]) {
					isDestroyed[i] = true;
					if (findCycle(buildingsDestroyed + 1, isDestroyed, i, first)) {
						return true;
					}
					isDestroyed[i] = false;
				}
			}
		}
		return false;

	}
}
