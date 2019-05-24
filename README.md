# CommonRecyclerView
通用的RecyclerView，实现了RecyclerView、GridView、ViewPager功能

How to
To get a Git project into your build:

Step 1. Add the JitPack repository to your build file

gradle
maven
sbt
leiningen
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.bsmmaoshenbo:CommonRecyclerView:Tag'
	}
