/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.codelabs.navigation

import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.*
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected

/**
 * Fragment used to show how to navigate to another destination
 */
class HomeFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // DSL
        val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }
//        val ops = NavOptions.Builder()
//            .setEnterAnim(R.anim.slide_out_right)
//            .build()
        val button = view.findViewById<Button>(R.id.navigate_destination_button)
        button?.setOnClickListener {
            findNavController().navigate(
                R.id.flow_step_one_dest,
                Bundle().apply { putInt("flowStepNumber", 1) },
                options
            )
        }
//        button?.setOnClickListener(
//            // Why there's no this?
//            Navigation.createNavigateOnClickListener(R.id.flow_step_one_dest, null, options)
//        )
//        view.findViewById<Button>(R.id.navigate_action_button)?.setOnClickListener(
//            Navigation.createNavigateOnClickListener(R.id.next_action, null)
//        )
        button?.setOnClickListener(
            Navigation.createNavigateOnClickListener(HomeFragmentDirections.nextAction(1))
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Have the NavigationUI look for an action or destination matching the menu
        // item id and navigate there if found.
        // Otherwise, bubble up to the parent.
        return item.onNavDestinationSelected(findNavController())
                || super.onOptionsItemSelected(item)
    }
}
