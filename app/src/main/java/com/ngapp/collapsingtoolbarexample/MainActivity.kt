package com.ngapp.collapsingtoolbarexample

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.ngapp.collapsingtoolbarexample.databinding.ActivityCoordinatorBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoordinatorBinding

    private val users = listOf(
        "User1",
        "User2",
        "Unknown"
    )

    private val basic =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam laoreet quam sed viverra varius. Nam justo lorem, accumsan eget elit id, cursus facilisis eros. Phasellus pretium tellus id congue fermentum. Phasellus vitae felis at mauris elementum rhoncus. Ut placerat, velit sed semper tempus, dui orci malesuada enim, vel facilisis nibh augue nec augue. Integer luctus sollicitudin elit, a euismod felis pharetra nec. Mauris commodo molestie velit non vulputate.\n" +
                "\n" +
                "Sed ultricies tincidunt ultricies. Aenean commodo, mi vel efficitur molestie, augue nibh ultricies ipsum, vel convallis odio purus vitae tortor. Cras vel euismod est. Phasellus viverra id dolor sed pellentesque. Nulla placerat magna justo, id fermentum justo euismod at. Integer urna lorem, dictum id nisi ut, finibus pellentesque sem. Ut eu augue a diam pretium tristique at id enim. Donec dapibus placerat eros non vehicula. Sed fermentum urna eget nisl iaculis, cursus pulvinar diam condimentum. Pellentesque vel semper dui. Aenean risus lacus, faucibus at turpis id, ullamcorper sollicitudin velit. Nulla nec magna pulvinar, tempor lectus in, luctus purus. Sed magna velit, posuere id lorem et, sagittis malesuada elit.\n" +
                "\n" +
                "Mauris tincidunt nisi ac leo iaculis, ac pharetra dolor convallis. Etiam congue arcu id faucibus dictum. Nam porta consectetur nisi id rhoncus. Phasellus dapibus congue massa, vitae tincidunt tellus. Integer id nisl tellus. Suspendisse rutrum efficitur orci, id faucibus odio aliquet et. Etiam a varius mauris. Maecenas placerat convallis turpis in porttitor. Pellentesque finibus at dui a tincidunt. Sed vel vulputate erat, at posuere augue. Cras imperdiet tempor purus ut eleifend. Nam venenatis lobortis erat, at commodo urna auctor eget. Pellentesque laoreet non leo id mattis. Donec ut faucibus sem.\n" +
                "\n" +
                "Quisque fermentum odio odio, sit amet auctor nisi vestibulum non. In id orci eget mauris placerat vehicula. Vivamus cursus cursus enim, vitae sodales ante convallis ut. Sed id felis dapibus, cursus leo eget, volutpat tellus. Fusce ex quam, sodales sit amet erat a, rhoncus pellentesque nisi. Suspendisse at magna metus. Cras aliquet malesuada hendrerit. Maecenas tellus eros, dapibus at nisi eu, laoreet feugiat libero. Praesent posuere massa purus, vel condimentum ante elementum vitae.\n" +
                "\n" +
                "Nunc eget sollicitudin felis. Nam fringilla ut nunc eget pellentesque. Sed in varius diam, vel hendrerit eros. Cras commodo, dolor non malesuada volutpat, mauris ligula fringilla nibh, sed placerat justo dolor at massa. Aenean gravida ipsum sed quam rutrum convallis. Sed laoreet erat non ultrices scelerisque. Nunc tempus neque et eros tempor pharetra a a felis. Nulla vitae lorem id libero elementum fringilla. In non est ligula. Aliquam erat volutpat. Curabitur faucibus, eros ac fermentum suscipit, eros orci malesuada sem, non porta enim sapien laoreet arcu. Etiam ut dolor vel sem sagittis tincidunt. Mauris ut imperdiet lacus. Nunc nec est non dui fringilla posuere."


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoordinatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.floatingButton.setOnClickListener {
            Toast.makeText(this, "Floating Button Clicked!", Toast.LENGTH_SHORT).show()
        }

        binding.contentText.text = basic
        initToolbar()
    }


    private fun initToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            Toast.makeText(this, "Navigation Clicked!", Toast.LENGTH_SHORT).show()
        }

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_1 -> {
                    Toast.makeText(this, "Action 1 Clicked!", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.action_2 -> {
                    Toast.makeText(this, "Action 2 Clicked!", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.action_settings -> {
                    Toast.makeText(this, "Settings Clicked!", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }


        val searchItem = binding.toolbar.menu.findItem(R.id.action_search)
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                binding.contentText.text = "search expanded"
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                binding.contentText.text = "search collapsed"
                return true
            }

        })

        (searchItem.actionView as SearchView).setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                users.filter { it.contains(newText ?: "", ignoreCase = true) }
                    .joinToString()
                    .let {
                        binding.contentText.text = it
                    }
                return true
            }
        })
    }
}
