#!/bin/bash

echo ">>>>>>>>>> executando comando <<<<<<<<"
DIR="../externar-script"
if [ -d "$DIR" ]; then
  echo "diretório existe ${DIR}..."
  echo "NVM Version: "
  nvm --version
  echo "Node Version: "
  node -v
  
else
  echo "Error: ${DIR} não existe"
  exit 1
fi
