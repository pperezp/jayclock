# Maintainer: Patricio Pérez Pinto <prezdev88@gmail.com>
# makepkg -si
pkgname=jayclock
pkgver=1.1.1
pkgrel=1
pkgdesc="A simple java clock"
arch=('any')
url="https://github.com/pperezp/jayclock"
license=('GPL3')
depends=('java-runtime' 'maven')

source=("$pkgname-$pkgver.tar.gz::https://github.com/pperezp/jayclock/archive/refs/tags/v$pkgver.tar.gz")

sha256sums=('40c612676c641ecbcae83bd8e63f68bf2f5a86ab71c230b3002a797d4aa5b7eb')

build() {
  cd "$srcdir/jayclock-$pkgver"
  mvn clean package
}

package() {
  mkdir -p "$pkgdir/usr/share/java/$pkgname-$pkgver"
  cp "$srcdir/jayclock-$pkgver/target/jayclock-1.0-SNAPSHOT-jar-with-dependencies.jar" "$pkgdir/usr/share/java/$pkgname-$pkgver/$pkgname-$pkgver.jar"

  mkdir -p "$pkgdir/usr/bin"
  echo "#!/bin/sh" > "$pkgdir/usr/bin/jayclock"
  echo "java -jar /usr/share/java/$pkgname-$pkgver/$pkgname-$pkgver.jar" >> "$pkgdir/usr/bin/jayclock"
  chmod +x "$pkgdir/usr/bin/jayclock"
}
