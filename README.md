# Power Breathing Protokoll – Capacitor App-Projekt

Dieses Verzeichnis enthält die Capacitor-Konfiguration, um die Web-App als native iOS- und Android-App zu verpacken und im App Store bzw. Google Play Store einzureichen.

---

## Voraussetzungen

| Tool | Version | Download |
|---|---|---|
| Node.js | ≥ 18.x | https://nodejs.org |
| npm | ≥ 9.x | (mit Node.js) |
| Xcode | ≥ 15 | Mac App Store (nur für iOS) |
| Android Studio | ≥ Hedgehog | https://developer.android.com/studio |
| CocoaPods | aktuell | `sudo gem install cocoapods` (nur für iOS) |

---

## Projektstruktur

```
capacitor-projekt/
├── package.json          # npm-Abhängigkeiten (Capacitor)
├── capacitor.config.json # App-ID, Name, Web-Verzeichnis
├── setup.sh              # Automatisches Setup-Script
├── README.md             # Diese Datei
└── www/                  # Hier kommt Ihre fertige Web-App hin
    └── index.html        # Einstiegspunkt der Web-App
```

---

## Schritt-für-Schritt-Anleitung

### 1. Web-App vorbereiten

Kopieren Sie Ihre fertige HTML/CSS/JS-App in den Ordner `www/`:

```bash
cp /pfad/zur/ihrer/app/index.html ./www/
cp /pfad/zur/ihrer/app/*.css ./www/
cp /pfad/zur/ihrer/app/*.js ./www/
```

Falls Sie die App `wimhof-app.html` oder `power-breathing-alle-dateien.zip` nutzen:

```bash
mkdir -p www
# Entpacken Sie Ihre App-Dateien und legen Sie index.html in www/ ab
```

### 2. Setup ausführen

```bash
bash setup.sh
```

Das Script:
- prüft Node.js
- installiert Capacitor-Pakete via `npm install`
- fügt iOS- und Android-Plattformen hinzu (`cap add ios`, `cap add android`)
- synchronisiert die Web-Dateien mit den nativen Projekten (`cap sync`)

### 3. iOS – Xcode öffnen

```bash
npm run ios
```

In Xcode:
1. Wählen Sie Ihr **Signing-Zertifikat** (Apple Developer Account erforderlich)
2. Stellen Sie die **Bundle ID** `de.coenen.atemprotokoll` sicher
3. Setzen Sie die **Version** auf `1.0` und **Build** auf `1`
4. Wählen Sie **Product → Archive** für die App Store-Einreichung
5. Laden Sie das Archiv über **Organizer → Distribute App** hoch

### 4. Android – Android Studio öffnen

```bash
npm run android
```

In Android Studio:
1. Warten Sie, bis das Gradle-Sync abgeschlossen ist
2. Gehen Sie zu **Build → Generate Signed Bundle / APK**
3. Wählen Sie **Android App Bundle (.aab)** – empfohlen für Play Store
4. Erstellen oder wählen Sie einen **Keystore** (sicher aufbewahren!)
5. Laden Sie das `.aab` im Google Play Console hoch

---

## App-Icon einbinden

Die App-Icons finden Sie unter `../icons/`. Fügen Sie sie wie folgt ein:

### iOS (Xcode)
- Öffnen Sie `App/App/Assets.xcassets/AppIcon.appiconset`
- Ersetzen Sie die Platzhalter durch die passenden PNG-Dateien aus `../icons/ios-*/`

### Android (Android Studio)
- Ersetzen Sie die Icons in:
  - `app/src/main/res/mipmap-mdpi/` → `icon_48.png`
  - `app/src/main/res/mipmap-hdpi/` → `icon_72.png`
  - `app/src/main/res/mipmap-xhdpi/` → `icon_96.png`
  - `app/src/main/res/mipmap-xxhdpi/` → `icon_144.png`
  - `app/src/main/res/mipmap-xxxhdpi/` → `icon_192.png`

---

## App Store Einreichung – Checkliste

### Apple App Store
- [ ] Apple Developer Account aktiv (99 $/Jahr)
- [ ] App in App Store Connect angelegt (Bundle-ID: `de.coenen.atemprotokoll`)
- [ ] Screenshots erstellt (mindestens iPhone 6.5" und 5.5")
- [ ] App-Icon 1024×1024 px (aus `../icons/icon_1024.png`) hochgeladen
- [ ] Datenschutzerklärung-URL hinterlegt: `https://passers-praxis.de/datenschutz`
- [ ] App-Beschreibung eingefügt (aus `../store-texte.md`)
- [ ] Altersfreigabe: 4+
- [ ] Kategorie: Gesundheit & Fitness

### Google Play Store
- [ ] Google Play Developer Account aktiv (25 $ einmalig)
- [ ] App in Play Console angelegt
- [ ] App Bundle (.aab) hochgeladen
- [ ] Store-Listing auf Deutsch ausgefüllt (aus `../store-texte.md`)
- [ ] Datenschutzerklärung-URL hinterlegt: `https://passers-praxis.de/datenschutz`
- [ ] Feature Graphic (1024×500 px) erstellt
- [ ] Screenshots hochgeladen (mindestens 2)
- [ ] Inhaltsbewertung ausgefüllt

---

## Kontakt & Support

**Praxis Team Dr. Anne Passers**  
E-Mail: ucoenen@gmail.com  
Web: https://passers-praxis.de
