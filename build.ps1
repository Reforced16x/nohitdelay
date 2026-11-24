$ErrorActionPreference = "Stop"

Write-Host "=== RNHD (Ornithe 1.8.9) Builder ===" -ForegroundColor Cyan

if (-not (Get-Command "javac" -ErrorAction SilentlyContinue)) {
    Write-Error "JDK not found. Install JDK 17 or 21 and ensure JAVA_HOME / PATH is configured."
    exit 1
}

$gradleVersion = "8.12.1"
$gradleDir = Join-Path $PSScriptRoot ".gradle-portable"
$gradleBin = Join-Path $gradleDir "gradle-$gradleVersion\bin\gradle.bat"

if (-not (Test-Path $gradleBin)) {
    Write-Host "Downloading portable Gradle $gradleVersion..." -ForegroundColor Yellow
    New-Item -ItemType Directory -Force -Path $gradleDir | Out-Null
    $zipPath = Join-Path $gradleDir "gradle.zip"
    $url = "https://services.gradle.org/distributions/gradle-$gradleVersion-bin.zip"
    Invoke-WebRequest -Uri $url -OutFile $zipPath
    Write-Host "Extracting Gradle..." -ForegroundColor Yellow
    Expand-Archive -Path $zipPath -DestinationPath $gradleDir -Force
    Remove-Item $zipPath
}

Write-Host "Building RNHD..." -ForegroundColor Green
& $gradleBin build --no-daemon

if ($LASTEXITCODE -ne 0) {
    Write-Host "`nBuild failed." -ForegroundColor Red
    exit $LASTEXITCODE
}

Write-Host "`nBuild complete. Output jar(s):" -ForegroundColor Cyan
Get-ChildItem -Path (Join-Path $PSScriptRoot "build\libs") -Filter "*.jar" | Where-Object { $_.Name -notlike "*-sources.jar" -and $_.Name -notlike "*-dev.jar" } | ForEach-Object {
    Write-Host " -> $($_.FullName)" -ForegroundColor Yellow
}
