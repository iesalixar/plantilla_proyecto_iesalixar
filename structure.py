import os

def print_structure(root_dir, indent=""):
    for item in sorted(os.listdir(root_dir)):
        path = os.path.join(root_dir, item)
        if os.path.isdir(path):
            print(f"{indent}📁 {item}")
            print_structure(path, indent + "    ")
        else:
            print(f"{indent}📄 {item}")

print_structure("./src/src-api/src/main/java/com/fitconnet")
