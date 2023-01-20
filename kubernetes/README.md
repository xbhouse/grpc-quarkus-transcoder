# Manifests for configuring gRPC on OpenShift with RHOSSM (Istio)

Run the following commands:

```
# Install the operators
oc apply -f mesh-operators.yaml

# Create the istio-system and app namespaces
oc new-project istio-system
oc new-project grpc-testing

# Apply the ServiceMeshControlPlane (it may take a few minutes for all the pods to be ready)
oc apply -f ServiceMeshControlPlane.yaml -n istio-system

# Enroll your app namespace in the mesh
oc apply -f ServiceMeshMemberRoll.yaml -n istio-system

# FOR TESTING ONLY: Generate a self-signed certificate and a secret referencing those values
mkdir tls
openssl req -newkey rsa:4096 -nodes -keyout tls/tls.key -x509 -days 365 -out tls/tls.crt
oc create secret tls tls-secret --cert=tls/tls.crt --key=tls/tls.key -n istio-system

# Apply the Gateway and Ingress (ensure the hosts are replaced with your own)
oc apply -f Gateway.yaml -f Ingress.yaml -n istio-system

# Apply the manifests to deploy your application to your app namespace (ensure the image is replaced with your own)
oc apply -f app-template-mesh.yaml -n grpc-testing

# Apply the VirtualService and EnvoyFilter (ensure the host is replaced with your own in the VirtualService)
oc apply -f VirtualService.yaml -f EnvoyFilter.yaml -n grpc-testing
```
