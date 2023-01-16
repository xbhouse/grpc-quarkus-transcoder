FROM registry.access.redhat.com/ubi8/ubi@sha256:68fecea0d255ee253acbf0c860eaebb7017ef5ef007c25bee9eeffd29ce85b29

#install the git and github clis
RUN dnf install -y git && \
dnf install -y 'dnf-command(config-manager)' && \
dnf config-manager --add-repo https://cli.github.com/packages/rpm/gh-cli.repo && \
dnf install -y gh

#install yq
RUN curl -L https://github.com/mikefarah/yq/releases/download/v4.25.1/yq_linux_amd64 -o yq && \
mv yq /usr/local/bin && \
chmod 555 /usr/local/bin/yq

#install unzip
RUN yum install unzip -y

#install protoc
RUN PB_REL="https://github.com/protocolbuffers/protobuf/releases" && \
curl -LO $PB_REL/download/v3.15.8/protoc-3.15.8-linux-x86_64.zip && \
unzip protoc-3.15.8-linux-x86_64.zip

#install go
RUN curl -OL https://golang.org/dl/go1.19.3.linux-amd64.tar.gz && \
rm -rf /usr/local/go && tar -C /usr/local -xzf go1.19.3.linux-amd64.tar.gz
