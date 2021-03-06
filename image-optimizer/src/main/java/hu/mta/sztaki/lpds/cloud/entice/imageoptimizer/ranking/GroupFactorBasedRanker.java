/*
 *    Copyright 2009-2015 Gabor Kecskemeti, University of Westminster, MTA SZTAKI
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package hu.mta.sztaki.lpds.cloud.entice.imageoptimizer.ranking;

import hu.mta.sztaki.lpds.cloud.entice.imageoptimizer.grouping.Group;
import hu.mta.sztaki.lpds.cloud.entice.imageoptimizer.grouping.Group.GroupState;

public class GroupFactorBasedRanker extends SizeBasedRanker {
	@Override
	public double rank(Group g) {
		double groupfactor = 1;
		if (g.parent != null && g.parent.children.size() != 0) {
			double failcount = 0, successcount = 0;
			for (Group sibling : g.parent.children) {
				failcount += sibling.getGroupState().equals(
						GroupState.REMOVAL_FAILURE) ? 0 : 1;
				successcount += sibling.getGroupState().equals(
						GroupState.REMOVAL_SUCCESS) ? 0 : 1;
				successcount += sibling.getGroupState().equals(
						GroupState.FINAL_VALIDATION_FAILURE) ? 0 : 1;
			}
			groupfactor = Math.min(1, 1 + (successcount - failcount)
					/ g.parent.children.size());
		}
		return groupfactor*super.rank(g);
	}
}
