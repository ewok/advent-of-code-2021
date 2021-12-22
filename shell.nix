with import <nixpkgs> {};

let
  clj = pkgs.clojure;
in
stdenv.mkDerivation rec {
  name = "clojure-environment";

  buildInputs = [
    clj
    pkgs.leiningen
  ];

  shellHook = ''
    echo "using clojure: ${clojure.name}"
  '';
}
