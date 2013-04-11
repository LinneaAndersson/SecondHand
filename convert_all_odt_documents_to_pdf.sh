#!/usr/bin/env bash

platform='unknown'
unamestr=$(uname)
if [[ "$unamestr" == 'Linux' ]]; then
    # if you're on linux I assume you have libreoffice on your path.
    libre_office_path='libreoffice'
elif [[ "$unamestr" == 'Darwin' ]]; then
   # If on OS X I assume you have put LibreOffice.app in the Applications/ directory
    libre_office_path='/Applications/LibreOffice.app/Contents/MacOS/soffice'
fi

declare -a arr=(rad meeting)

for dir in ${arr[@]}
do
    $libre_office_path --headless --convert-to pdf --outdir $dir $dir/*.odt
done

#$libre_office_path --headless --convert-to pdf --outdir meeting/ meeting/*.odt
