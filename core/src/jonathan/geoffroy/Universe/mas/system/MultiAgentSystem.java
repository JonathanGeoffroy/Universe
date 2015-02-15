package jonathan.geoffroy.Universe.mas.system;

import com.sun.istack.internal.NotNull;
import jonathan.geoffroy.Universe.mas.agents.Agent;

import java.util.Collections;
import java.util.List;
import java.util.Observable;

/**
 * Universe is a Multi-Agent system which describes a fictional city.
 * Copyright (C) 2015  Jonathan Geoffroy
 * <p/>
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * @author Jonathan Geoffroy
 */
public class MultiAgentSystem extends Observable {
    /**
     * Elapsed time from the beginning of the simulation.
     * Increased each time every agents have played
     */
    private long time;

    /**
     * List of all agents which can play into this Simulation
     */
    private List<Agent> agents;

    /**
     * True if this Simulation is currently running
     */
    private boolean running;

    public MultiAgentSystem(@NotNull List<Agent> agents) {
        this.agents = agents;
    }

    /**
     * Run simulation while running == true
     * i.e. call <code>run()</code> for each agent
     * increase <code>time</code>
     *
     * @see jonathan.geoffroy.Universe.mas.agents.Agent#run()
     */
    private void run() {
        while (running) {
            // Shuffle the list in order to equalize chances for each agent
            Collections.shuffle(agents);

            // Let play each agent
            for (Agent agent : agents) {
                agent.run();
            }

            time++;
        }
    }

    /**
     * Start / restart the simulation in a new thread
     * <code>running</code> should be false
     */
    public void start() {
        running = true;
        Thread runnable = new Thread() {
            @Override
            public void run() {
                MultiAgentSystem.this.run();
            }
        };
        runnable.start();
    }

    /**
     * Stop the simulation
     */
    public void stop() {
        running = false;
    }

    public long getTime() {
        return time;
    }
}
