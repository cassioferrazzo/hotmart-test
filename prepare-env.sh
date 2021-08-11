#!/bin/bash

echo ">>>>>>>>>> executando comando <<<<<<<<"
DIR="../external-script/"
if [ -d "$DIR" ]; then
  echo "diretório existe ${DIR}"
  echo "lendo arquivo ${DIR}file.txt
  cat "${DIR}file.txt"
  echo "Node Version: "
  node -v
  
else
  echo "Error: ${DIR} não existe"
  exit 1
fi
