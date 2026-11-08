# Glass Launcher — Todo & Milestones

**Project:** Glass Launcher (Android, Kotlin + Jetpack Compose)
**App ID:** `com.void.launcher`
**Min SDK:** 31 (Android 12) · **Compile/Target SDK:** 36
**Source of truth:** `design.md`

Status legend: `[ ]` not started · `[~]` in progress · `[x]` done

---

## M0 — Repository & Project Scaffolding

- [x] M0.1 Initialize git repository in project root
- [x] M0.2 Create public GitHub remote repository
- [x] M0.3 Push initial commit to remote
- [x] M0.4 Create root `build.gradle.kts`
- [x] M0.5 Create `settings.gradle.kts`
- [x] M0.6 Create `gradle/libs.versions.toml` (version catalog)
- [x] M0.7 Create `gradle/wrapper/gradle-wrapper.properties`
- [x] M0.8 Create `app/build.gradle.kts`
- [x] M0.9 Create `app/proguard-rules.pro`
- [x] M0.10 Create `app/src/main/AndroidManifest.xml`
- [x] M0.11 Create `GlassLauncherApp.kt` (Application class)
- [x] M0.12 Create `MainActivity.kt`
- [x] M0.13 Create resource files (`strings.xml`, `colors.xml`, `themes.xml`)
- [x] M0.14 Create `local.properties` (sdk.dir) — gitignored
- [x] M0.15 First successful `./gradlew assembleDebug`
- [ ] M0.16 Commit & push scaffolding

## M1 — Design System / Theme

- [x] M1.1 Color tokens (`ui/theme/Color.kt`)
- [x] M1.2 Typography (`ui/theme/Type.kt`)
- [x] M1.3 Shape / radius tokens (`ui/theme/Shape.kt`)
- [ ] M1.4 Spacing constants (`ui/theme/Spacing.kt`)
- [x] M1.5 Glass material modifier (`Modifier.glass(...)`) — see `ui/theme/Glass.kt`
- [x] M1.6 Accent color state + provider (`LocalAccent`)
- [x] M1.7 `GlassLauncherTheme` composable
- [ ] M1.8 Reduced-motion handling
- [ ] M1.9 Commit & push

## M2 — Data Model

- [ ] M2.1 `AppInfo` data class
- [ ] M2.2 Sample apps list (22 apps, brand gradients, icons)
- [ ] M2.3 Dock items data
- [ ] M2.4 Folder + folder contents
- [ ] M2.5 Hidden apps set
- [ ] M2.6 Notification badges map
- [ ] M2.7 Contacts list (4)
- [ ] M2.8 Tracks list (4 synthetic tracks)
- [ ] M2.9 Settings items list (6)
- [ ] M2.10 `LauncherState` / `LauncherViewModel`
- [ ] M2.11 Persistence via DataStore (blur, opacity, accent, gesture mode, dock/folder/hidden)
- [ ] M2.12 Commit & push

## M3 — Home Screen (Page 0)

- [x] M3.1 Status bar composable
- [x] M3.2 Clock card (time, date, glance chips)
- [x] M3.3 Glance chips (weather, next event, battery)
- [x] M3.4 Now Playing card (art, title/artist, transport, progress)
- [x] M3.5 Search bar (always visible)
- [ ] M3.6 Search results overlay
- [x] M3.7 Dock (3 apps + Social folder)
- [ ] M3.8 Folder popup (4-column grid)
- [x] M3.9 Page indicator dots
- [x] M3.10 Hint text (contextual)
- [x] M3.11 Animated background blobs
- [x] M3.12 Home screen assembly + vertical stack
- [ ] M3.13 Commit & push

## M4 — Widgets Page (Page 1)

- [x] M4.1 "Today" title
- [x] M4.2 Weather card
- [x] M4.3 Calendar events card
- [x] M4.4 Battery / screen-time card
- [x] M4.5 Quick note card
- [x] M4.6 Page swipe Home ↔ Widgets
- [ ] M4.7 Commit & push

## M5 — App Drawer

- [ ] M5.1 Full-height overlay slide-up
- [ ] M5.2 Search field at top
- [ ] M5.3 Alphabetical list with sticky letter headers
- [ ] M5.4 Right-edge A–Z index
- [ ] M5.5 Handle / close bar at bottom
- [ ] M5.6 Dismiss (tap handle / swipe down at top)
- [ ] M5.7 Hidden-apps visibility glyph
- [ ] M5.8 Hide/unhide individual apps
- [ ] M5.9 Commit & push

## M6 — Settings Panel

- [ ] M6.1 Top-right gear entry point
- [ ] M6.2 Drawer open method selector (4 options)
- [ ] M6.3 Glass blur slider (6–50)
- [ ] M6.4 Glass opacity slider (3–25%)
- [ ] M6.5 Accent color picker (4 choices)
- [ ] M6.6 Live preview of blur/opacity/accent
- [ ] M6.7 Commit & push

## M7 — Context Menu

- [ ] M7.1 Long-press (450 ms) / right-click trigger
- [ ] M7.2 Movement > 10 px cancels timer
- [ ] M7.3 Swallow click after long-press
- [ ] M7.4 Menu positioning near touch, edge-constrained
- [ ] M7.5 Dynamic actions (shortcuts, pin/unpin, folder, hide/unhide, App info)
- [ ] M7.6 Commit & push

## M8 — Toast

- [ ] M8.1 Toast composable (fade + slight rise, 250 ms)
- [ ] M8.2 Trigger on key actions
- [ ] M8.3 Commit & push

## M9 — Gestures & Navigation

- [ ] M9.1 Swipe up/left to open drawer per mode
- [ ] M9.2 Tap search opens drawer
- [ ] M9.3 Handle button opens drawer
- [ ] M9.4 Page navigation (horizontal swipe / dots)
- [ ] M9.5 Drawer dismiss gestures
- [ ] M9.6 Commit & push

## M10 — Search Behavior

- [ ] M10.1 Live filter (apps, contacts, settings, calculator, timer, web)
- [ ] M10.2 Enter selects first result
- [ ] M10.3 Math expression parser
- [ ] M10.4 Timer command parser (`timer 5`)
- [ ] M10.5 Web search fallback
- [ ] M10.6 Clearing field hides results
- [ ] M10.7 Commit & push

## M11 — Music Playback Simulation

- [ ] M11.1 Play/pause toggles interval progress
- [ ] M11.2 Previous resets to 0 if > 3 s else previous track
- [ ] M11.3 Next track
- [ ] M11.4 Scrubbable progress bar
- [ ] M11.5 Auto-advance on track end
- [ ] M11.6 Commit & push

## M12 — Accessibility

- [ ] M12.1 Content descriptions on icon-only buttons
- [ ] M12.2 Min touch targets ≥ 38–44 px
- [ ] M12.3 `prefers-reduced-motion` / reduce-motion toggle
- [ ] M12.4 Focusable controls + keyboard navigation
- [ ] M12.5 Commit & push

## M13 — Build, Test & Quality

- [ ] M13.1 Unit tests for search parsers
- [ ] M13.2 Unit tests for music logic
- [ ] M13.3 UI tests for home render
- [ ] M13.4 Lint clean (`./gradlew lint`)
- [ ] M13.5 Release build (`./gradlew assembleRelease`)
- [ ] M13.6 Commit & push

## M14 — Final Polish & Release

- [ ] M14.1 Adaptive launcher icon
- [ ] M14.2 Splash screen
- [ ] M14.3 README with screenshots
- [ ] M14.4 Tag v1.0.0
- [ ] M14.5 Final push

---

## Notes / Decisions

- App ID: `com.void.launcher`
- Min SDK 31; compile/target SDK 36.
- Glass blur uses `RenderEffect` on API 31+ with fallback.
- Accent palette: Violet (default), Cyan, Magenta, Green.
- No HTML mockup in repo (Kotlin only).
- Remote: public GitHub repo under `ngbcoder`.
