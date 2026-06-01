#!/bin/bash
# Power Breathing Protokoll - App Setup
# Ausführen mit: bash setup.sh

echo "🌿 Power Breathing Protokoll - App Store Setup"
echo "============================================="

# Prüfe Node.js
if ! command -v node &> /dev/null; then
    echo "❌ Node.js nicht gefunden. Bitte von https://nodejs.org installieren."
    exit 1
fi
echo "✅ Node.js $(node --version) gefunden"

# Installiere Capacitor
echo "📦 Installiere Capacitor..."
npm install

# Initialisiere Capacitor (falls noch nicht geschehen)
if [ ! -d "ios" ] && [ ! -d "android" ]; then
    echo "🔧 Füge iOS und Android hinzu..."
    npx cap add ios
    npx cap add android
fi

# Kopiere Web-Dateien
echo "🔄 Synchronisiere Web-Dateien..."
npx cap sync

echo ""
echo "✅ Fertig! Nächste Schritte:"
echo "   iOS:     npm run ios     (öffnet Xcode)"
echo "   Android: npm run android (öffnet Android Studio)"
