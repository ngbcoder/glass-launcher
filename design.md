# Glass Launcher — Design Document

**Version:** 1.0  
**Status:** Concept / High-Fidelity Mockup  
**Platform:** Android (phone-first)  
**Date:** November 2026  

---

## 1. Vision & Product Intent

Glass Launcher is a modern, gesture-first Android home-screen experience built around **glassmorphism**. It prioritizes:

- Visual calm and depth over dense icon grids
- Fast access to apps, search, and media without leaving the home surface
- Customizable glass material properties and interaction models
- A cohesive “living” background that reacts subtly to time and motion

The launcher aims to feel premium, fluid, and personal — closer to a crafted instrument than a utility grid.

---

## 2. Design Principles

| Principle | Description |
|-----------|-------------|
| **Material honesty** | Glass surfaces are translucent, edge-lit, and sit in front of a living background. Opacity and blur are tunable so the user owns the material. |
| **Glanceable hierarchy** | Time, weather, calendar, and now-playing are primary. Secondary actions live behind intentional gestures. |
| **Gesture over chrome** | Primary navigation uses swipe and long-press. Persistent UI is minimal. |
| **One accent, many voices** | A single user-chosen accent color ties progress indicators, active states, and highlights together. |
| **Progressive disclosure** | Search, app drawer, folders, and context menus appear only when needed. |
| **Motion with restraint** | Background blobs animate slowly. Transitions use a consistent ease curve. Reduced-motion is respected. |

---

## 3. Visual Design System

### 3.1 Color Tokens

```css
--bg:          #05060d / #05070f   /* deep near-black canvas */
--text:        #ffffff
--muted:       rgba(255,255,255,0.55)
--glass:       rgba(255,255,255,0.07)   /* tunable 3–25% */
--edge:        rgba(255,255,255,0.18)
--hi:          rgba(255,255,255,0.30)   /* top inset highlight */
--accent:      #8b7cff                  /* default; user-selectable */
```

**Accent palette (selectable):**
- Violet `#8b7cff` (default)
- Cyan `#4cc9f0`
- Magenta `#f72585`
- Green `#7ae582`

App icons use individual brand-tinted gradients derived from a base hue, never pure flat colors.

### 3.2 Glass Material

Every primary surface uses the shared `.glass` treatment:

- Background: `var(--glass)`
- Backdrop filter: `blur(var(--blur)) saturate(180%)` (default blur 22 px, range 6–50)
- Border: 1 px solid `var(--edge)`
- Inset highlights + soft outer shadow for depth

This creates a consistent “frosted glass” language across clock, music player, search, dock, drawer, widgets, and menus.

### 3.3 Typography

- System font stack: `system-ui, -apple-system, "Segoe UI", Roboto, sans-serif`
- Clock: 64 px / weight 300 / tight tracking
- Primary labels: 15–16 px / semi-bold
- Secondary / muted: 13 px
- Micro: 11–12 px
- Letter sections in drawer: 13 px bold, uppercase, muted

### 3.4 Iconography

Material Icons (filled) at consistent optical sizes:
- Dock / app icons: 26 px inside 52 × 52 px containers
- Row icons: 22 px inside 38 × 38 px
- Status bar: 16 px
- Glance chips: 16 px

Icons sit on subtle gradient tiles with a 1 px light border and soft drop shadow.

### 3.5 Spacing & Radius

- Phone frame radius: 44 px
- Large cards (clock, drawer panels): 24–28 px
- Dock: 32 px
- Search / pills: 99 px (full pill)
- App icons: 16 px
- Consistent horizontal padding: 20 px
- Safe-area aware top/bottom insets

### 3.6 Motion

- Background blobs: independent 19–27 s ease-in-out alternate loops with scale + translate
- Drawer / widgets: 350 ms cubic-bezier(0.2, 0.8, 0.2, 1)
- Toast: 250 ms fade + slight rise
- Respects `prefers-reduced-motion: reduce` (blobs freeze)

---

## 4. Information Architecture & Screens

### 4.1 Home (Page 0)

Vertical stack inside the phone frame:

1. **Status bar** — time + signal + battery (system-like)
2. **Clock card** — large time, date, glance chips (weather, next event, battery %)
3. **Now Playing card** — artwork, title/artist, transport controls, scrubbable progress
4. **Flexible spacer**
5. **Hint text** (contextual to current open-drawer gesture)
6. **Search results overlay** (appears above search when typing)
7. **Search bar** — always visible
8. **Dock** — 3 pinned apps + 1 folder (“Social”)

Page indicator dots sit near the top (Home / Widgets).

### 4.2 Widgets (Page 1)

Accessed by swipe-right from home or tapping the second page dot.

- Title: “Today”
- Stack of glass cards: weather, calendar events, battery/screen-time, quick note
- Same glass treatment; content is glanceable and lightly interactive

### 4.3 App Drawer

Full-height overlay that slides up from the bottom (or left, depending on setting).

- Search field at top
- Alphabetical list with sticky letter headers
- Right-edge A–Z index for quick jump
- Handle / close bar at bottom
- Supports hide/unhide of individual apps

### 4.4 Supporting Surfaces

- **Settings panel** — top-right gear; controls gesture mode, blur, opacity, accent
- **Folder popup** — expands the Social folder into a 4-column grid
- **Context menu** — long-press / right-click on any app icon
- **Toast** — transient confirmation feedback

---

## 5. Core Components

### 5.1 Clock Widget
- Large light weight time (no AM/PM in the big numeral)
- Full weekday + month + day
- Horizontal glance chips: weather, next calendar event, battery

### 5.2 Music Player
- Album art tile with dynamic gradient
- Title + artist
- Previous / Play-Pause / Next
- Progress bar (scrubbable) + current / duration times
- Plays a short simulated track list with auto-advance

### 5.3 Search
- Global search bar on home
- Live results for:
  - Apps
  - Contacts
  - Settings shortcuts
  - Calculator expressions
  - Timer commands (`timer 5`)
  - Web search fallback
- Enter selects the first result

### 5.4 Dock & Folders
- Up to 4 dock items (currently 3 apps + 1 folder)
- Folder uses a 2×2 micro-grid of icons
- Long-press any app for pin/unpin, add/remove from folder, hide, shortcuts, info

### 5.5 App Icons
- 52 × 52 px rounded square
- Gradient fill derived from brand color
- Optional notification badge (red pill, top-right)
- Label underneath (11 px)

### 5.6 App Drawer Rows
- Icon + name
- Hidden apps show a visibility-off glyph
- Letter section headers

### 5.7 Context Menu
- App name header
- Dynamic actions: app shortcuts, pin/unpin, folder membership, hide/unhide, App info
- Positioned near the touch point, constrained to screen edges

---

## 6. Interaction Model

### 6.1 Opening the App Drawer (user-selectable)

| Mode | Gesture / Affordance |
|------|----------------------|
| Swipe up (default) | Swipe up on home |
| Swipe left | Swipe left on home |
| Tap search | Focus on search bar opens drawer |
| Handle button | Persistent bottom handle |

Drawer can be dismissed by:
- Tapping the bottom handle
- Swiping down when scrolled to top

### 6.2 Page Navigation
- Home ↔ Widgets via horizontal swipe or page dots
- Widgets page also supports swipe-left to return home

### 6.3 Long-Press / Context
- 450 ms long-press or right-click on any `[data-n]` element opens the context menu
- Movement > 10 px cancels the long-press timer
- Click after long-press is swallowed to prevent accidental launch

### 6.4 Search Behavior
- Typing immediately surfaces results above the search bar
- Clearing the field hides results
- Special parsers for math and timer commands

### 6.5 Music Controls
- Play/pause toggles interval-based progress
- Previous resets to 0 if > 3 s into track, otherwise previous track
- Progress bar is clickable for scrubbing

### 6.6 Settings Live Preview
- Blur and opacity ranges update CSS custom properties in real time
- Accent color buttons immediately recolor progress fills and active states

---

## 7. Content & Data Model (Mock)

**Apps** — 22 sample apps with unique colors and Material icon mappings.  
**Dock** — Phone, Chrome, Camera + Social folder.  
**Folder contents** — Messages, Telegram, Gmail.  
**Hidden by default** — Wallet.  
**Badges** — Messages (3), Gmail (12), Telegram (1), Phone (2).  
**Contacts** — Alex Kim, Jordan Lee, Mina Park, Sam Rivera.  
**Tracks** — four synthetic tracks with durations and gradient pairs.  
**Settings items** — Wi-Fi, Bluetooth, Display, Battery, Sound, Notifications.

All state (dock membership, folder membership, hidden set, accent, blur, opacity, gesture mode) is held in memory for the mockup.

---

## 8. Accessibility & Inclusive Design

- High-contrast text on dark glass
- `prefers-reduced-motion` disables blob animation
- Focusable controls with clear hit targets (≥ 38–44 px)
- Aria labels on icon-only buttons (settings, transport, close, page dots)
- Search and drawer inputs support standard keyboard / IME
- Color is never the sole indicator (badges, active states also use shape/position)

Future production considerations:
- TalkBack / screen-reader labels for every interactive surface
- Larger text scaling support
- High-contrast / forced-colors mode
- Haptic feedback on key gestures

---

## 9. Technical Notes (Mock → Production)

| Area | Mock Implementation | Production Direction |
|------|---------------------|----------------------|
| Rendering | Pure HTML/CSS/JS single file | Kotlin + Jetpack Compose or Flutter with custom renderers |
| Glass | CSS `backdrop-filter` | Android `RenderEffect` / Compose blur + layered surfaces |
| Icons | Material Icons web font | Material Symbols or custom adaptive icons |
| Background | CSS animated blobs | Live wallpaper or Compose Canvas with slow particle/gradient system |
| State | In-memory JS | ViewModel + DataStore / Room for persistence |
| Search | Client-side filter + simple parsers | System Search / AppSearch + custom providers |
| Gestures | Pointer events | Android GestureDetector / Compose pointerInput |

Performance targets for production:
- 60 fps continuous on mid-range devices
- Glass blur cost carefully managed (layer caching, reduced radius on low-end)
- Cold start under 800 ms

---

## 10. Customization Surface

Exposed in the settings panel:

1. **Drawer open method** — 4 mutually exclusive options
2. **Glass blur** — 6–50 px continuous
3. **Glass opacity** — 3–25 % continuous
4. **Accent color** — 4 discrete choices

These four controls give users strong ownership of both the material and the interaction model without overwhelming the settings surface.

---

## 11. Success Metrics (Future)

- Time-to-app-launch from home (median)
- Drawer open success rate by gesture mode
- Search-to-action conversion
- Retention of custom blur / accent settings
- Qualitative “feels premium / calm / fast” scores from usability testing

---

## 12. Open Questions & Next Steps

- Should the widgets page support user-reordering and third-party widgets?
- Deep integration with Android’s media session and notification shade?
- Adaptive icons vs. the current tinted Material icon style?
- Support for foldables / tablets (multi-column drawer, larger dock)?
- Theming beyond a single accent (full wallpaper-driven color extraction)?

**Recommended next artifacts**
1. High-fidelity interactive prototype (current mockup is a strong start)
2. Component library in Figma with glass material variants
3. Motion spec with exact curves and durations
4. Accessibility audit checklist
5. Technical spike on Android `RenderEffect` glass performance

---

*This document describes the design intent and concrete implementation visible in the current Glass Launcher Mockup. It is intended as the single source of truth for visual language, interaction model, and component behavior as the project moves toward a production Android launcher.*
