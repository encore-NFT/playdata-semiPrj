FROM cluster-base

# -- Layer: JupyterLab

ARG spark_version=3.1.2
ARG jupyterlab_version=2.2.6

RUN apt-get update -y && \
    apt-get install -y python3-pip && \
    pip3 install --upgrade pip setuptools wheel &&\
    pip3 install pandas &&\
    # pip3 install scikit-learn==0.22.1 &&\
    # pip3 install tensorflow &&\
    # pip3 install torch &&\
    pip3 install wget pyspark==${spark_version} jupyterlab==${jupyterlab_version}

# -- Runtime

EXPOSE 8888
WORKDIR ${SHARED_WORKSPACE}
CMD jupyter lab --ip=0.0.0.0 --port=8888 --no-browser --allow-root --NotebookApp.token=