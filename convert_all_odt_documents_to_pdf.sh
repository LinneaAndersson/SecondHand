#!/usr/bin/env bash

/Applications/LibreOffice.app/Contents/MacOS/soffice --headless --convert-to pdf --outdir rad/ rad/*.odt
/Applications/LibreOffice.app/Contents/MacOS/soffice --headless --convert-to pdf --outdir meeting/ meeting/*.odt
